package com.vn.osp.notarialservices.citizenVerificationNumber.repository;
import com.vn.osp.notarialservices.citizenVerificationNumber.domain.CitizenVerifyNumberBO;
import com.vn.osp.notarialservices.citizenVerificationNumber.dto.CitizenVerifyNumberDTO;
import com.vn.osp.notarialservices.utils.TimeUtil;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CitizenVerficationNumberRepositoryImpl implements CitizenVerficationNumberRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CitizenVerficationNumberRepositoryImpl.class);
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    @Override
    public Optional<CitizenVerifyNumberBO> saveOrUpdate(CitizenVerifyNumberBO citizenVerifyNumberBO, boolean isPlusTurn){
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        try {
            entityManagerCurrent.getTransaction().begin();
            CitizenVerifyNumberBO verifyNumberBO = checkNotaryOfficeCodeExits(citizenVerifyNumberBO.getNotary_office_code(),citizenVerifyNumberBO.getProvince_code()).size() > 0 ? checkNotaryOfficeCodeExits(citizenVerifyNumberBO.getNotary_office_code(),citizenVerifyNumberBO.getProvince_code()).get(0) : new CitizenVerifyNumberBO();
            if(verifyNumberBO.getNotary_office_code() != null && !verifyNumberBO.getNotary_office_code().equals("")
              && verifyNumberBO.getProvince_code() != null && !verifyNumberBO.getProvince_code().equals("")){
                // case thêm số lượt xác thực
                if(isPlusTurn){
                    // cập nhật số lượt xác thực khi thanh toán thành công
                    citizenVerifyNumberBO.setVerify_number_total(verifyNumberBO.getVerify_number_total() + citizenVerifyNumberBO.getVerify_number_total());
                    citizenVerifyNumberBO.setEntry_date_time(verifyNumberBO.getEntry_date_time());
                    citizenVerifyNumberBO.setEntry_user_name(verifyNumberBO.getEntry_user_name());
                    citizenVerifyNumberBO.setUpdate_date_time(TimeUtil.getTimeNow());
                    citizenVerifyNumberBO.setUpdate_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
                    entityManagerCurrent.merge(citizenVerifyNumberBO);
                }
                // case trừ số lượt xác thực khi xác thực thành công
                else {
                    verifyNumberBO.setVerify_number_total(verifyNumberBO.getVerify_number_total() - 1);
                    verifyNumberBO.setUpdate_date_time(TimeUtil.getTimeNow());
                    verifyNumberBO.setUpdate_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
                    citizenVerifyNumberBO = entityManagerCurrent.merge(verifyNumberBO);
                }
            }else {
                citizenVerifyNumberBO.setEntry_date_time(TimeUtil.getTimeNow());
                citizenVerifyNumberBO.setEntry_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
                entityManagerCurrent.persist(citizenVerifyNumberBO);
            }
            entityManagerCurrent.getTransaction().commit();
        }catch (Exception e){
            LOG.error("Have an error in method CitizenVerficationNumberRepositoryImpl.saveOrUpdate: "+e.getMessage());
            return Optional.of(new CitizenVerifyNumberBO());
        }finally {
            entityManagerCurrent.clear();
            entityManagerCurrent.close();
        }
        return Optional.of(citizenVerifyNumberBO);
}
    @Override
    public Optional<List<CitizenVerifyNumberBO>> getCitizenVerifyNumberByFilter(String stringFilter){
        List<CitizenVerifyNumberBO> results = new ArrayList<>();
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        entityManagerCurrent.getTransaction().begin();
        try {
            String hql = "select cvn from CitizenVerifyNumberBO cvn " + stringFilter;
            results = entityManagerCurrent.createQuery(hql).getResultList();
            entityManagerCurrent.close();
        } catch (Exception e) {
            LOG.error("Have an error in method CitizenVerficationNumberRepositoryImpl.getCitizenVerifyNumberByFilter: " + e.getMessage());
            entityManagerCurrent.getTransaction().rollback();
            entityManagerCurrent.close();
            return Optional.of(new ArrayList<>());
        }
        return Optional.of(results);
    }

    @Override
    public Optional<CitizenVerifyNumberBO> get(String notaryOfficeCode, String provinceCode) {
        CitizenVerifyNumberBO result;
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        entityManagerCurrent.getTransaction().begin();
        try {
            String hql = "select cvn from CitizenVerifyNumberBO cvn where 1=1 ";
            if(StringUtils.isNotBlank(notaryOfficeCode)){
                hql += " and cvn.notary_office_code = '" + notaryOfficeCode +"'";
            }
            if(StringUtils.isNotBlank(provinceCode)){
                hql += " and cvn.province_code = '" + provinceCode +"'";
            }
            result = (CitizenVerifyNumberBO) entityManagerCurrent.createQuery(hql).getSingleResult();
            entityManagerCurrent.close();
        } catch (Exception e) {
            LOG.error("Have an error in method CitizenVerficationNumberRepositoryImpl.get: " + e.getMessage());
            entityManagerCurrent.getTransaction().rollback();
            entityManagerCurrent.close();
            return Optional.empty();
        }
        return Optional.of(result);
    }

    @Override
    public Optional<CitizenVerifyNumberDTO> getDetail(String notaryOfficeCode, String provinceCode) {
        if(StringUtils.isBlank(notaryOfficeCode)) return Optional.empty();
        String hql = "select cus.code, cus.name, cus.province_code, " +
                "(select count(verify_id) from npo_citizen_verification_orders sub_orders join npo_order_map_verification sub_map on sub_orders.order_id = sub_map.order_id " +
                "where sub_orders.notary_office_code = cus.code and verify_status = 0) as verify_number " +
                "from npo_customer cus where cus.code = '" + notaryOfficeCode +"'";
        if(StringUtils.isNotBlank(provinceCode)) {
            hql += " and cus.province_code = '" + provinceCode +"'";
        }
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        entityManagerCurrent.getTransaction().begin();
        CitizenVerifyNumberDTO citizenVerifyNumberDTO = new CitizenVerifyNumberDTO();
        try {
            LOG.info(hql);
            Query query = entityManagerCurrent.createNativeQuery(hql);
            Object[] result = (Object[]) query.getSingleResult();
            if(result != null && result.length > 0) {
                citizenVerifyNumberDTO.setNotary_office_code((String) result[0]);
                citizenVerifyNumberDTO.setNotary_office_name((String) result[1]);
                citizenVerifyNumberDTO.setProvince_code((String) result[2]);
                citizenVerifyNumberDTO.setVerify_number_total(((BigInteger) result[3]).longValue());
            }
        } catch (Exception e) {
            LOG.error("Have an error in method CitizenVerficationNumberRepositoryImpl.getDetail: " + e.getMessage());
            entityManagerCurrent.getTransaction().rollback();
            entityManagerCurrent.close();
            return Optional.empty();
        }
        return Optional.of(citizenVerifyNumberDTO);
    }

    @Override
    public List<CitizenVerifyNumberBO> checkNotaryOfficeCodeExits(String notary_office_code, String province_code){
        List<CitizenVerifyNumberBO> result = new ArrayList<>();
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        entityManagerCurrent.getTransaction().begin();
        try {
            String hql = "select cvn from CitizenVerifyNumberBO cvn where cvn.notary_office_code = '"+notary_office_code+"' and cvn.province_code = '"+province_code+"'";
            result = (List<CitizenVerifyNumberBO>) entityManagerCurrent.createQuery(hql).getResultList();
            entityManagerCurrent.close();
        } catch (Exception e) {
            LOG.error("Have an error in method CitizenVerficationNumberRepositoryImpl.checkNotaryOfficeCodeExits: " + e.getMessage());
            entityManagerCurrent.getTransaction().rollback();
            entityManagerCurrent.close();
            return new ArrayList<>();
        }
        return result;
    }
}
