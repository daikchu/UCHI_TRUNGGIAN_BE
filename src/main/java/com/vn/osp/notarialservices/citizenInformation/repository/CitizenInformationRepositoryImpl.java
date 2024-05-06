package com.vn.osp.notarialservices.citizenInformation.repository;
import com.vn.osp.notarialservices.citizenInformation.domain.CitizenInfoBO;
import com.vn.osp.notarialservices.citizenVerificationNumber.domain.CitizenVerifyNumberBO;
import com.vn.osp.notarialservices.paymentTransaction.domain.OrderMapVerification;
import com.vn.osp.notarialservices.utils.TimeUtil;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CitizenInformationRepositoryImpl implements CitizenInformationRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CitizenInformationRepositoryImpl.class);
    @PersistenceContext
    private EntityManager entityManager;
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    @Override
    public Optional<CitizenInfoBO> saveOrUpdate(CitizenInfoBO citizenInfoBO) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        entityManagerCurrent.getTransaction().begin();
        List<CitizenInfoBO> result = new ArrayList<>();
        try {
            String hql = "select ct from CitizenInfoBO ct where ct.cccd_number = '"+citizenInfoBO.getCccd_number()+"' or ct.cmnd_number = '"+citizenInfoBO.getCmnd_number()+"'";
            result = entityManagerCurrent.createQuery(hql).getResultList();
            if(result != null && result.size() > 0) {
                // cập nhật số lần xác thực cho công dân
                citizenInfoBO.setVerified_count(result.get(0).getVerified_count() + citizenInfoBO.getVerified_count());
                citizenInfoBO.setUpdate_date_time(TimeUtil.getTimeNow());
                citizenInfoBO.setUpdate_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
                entityManagerCurrent.merge(citizenInfoBO);
            }else {
                citizenInfoBO.setEntry_date_time(TimeUtil.getTimeNow());
                citizenInfoBO.setEntry_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
                entityManagerCurrent.persist(citizenInfoBO);
            }
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Have an error in method CitizenInformationRepositoryImpl.save: " + e.getMessage());
            throw e;
        } finally {
            entityManagerCurrent.clear();
            entityManagerCurrent.close();
        }
        return Optional.of(citizenInfoBO);
    }
    @Override
    public Optional<OrderMapVerification> getVerifyIdMapOrderId(String notary_office_code) {
        try{
            String hql = "select map.id, map.order_id, map.verify_id, map.verify_status from npo_citizen_verification_orders o\n" +
                    "inner join npo_payment_transactions p on o.order_id = p.order_id\n" +
                    "inner join npo_order_map_verification map ON p.order_id = map.order_id\n" +
                    "where o.notary_office_code = '" + notary_office_code + "' and p.transaction_status = 'success'\n" +
                    "and map.verify_status = 0\n" +
                    "ORDER BY p.update_date_time DESC, p.entry_date_time DESC, map.id ASC\n" +
                    "LIMIT 1";
            LOG.info(hql);
            Query query = entityManager.createNativeQuery(hql);
            Object[] result = (Object[]) query.getSingleResult();
            OrderMapVerification map = mapDataFromDbQueryResult(result);
            return Optional.of(map);
        }catch (Exception ex){
            LOG.error("Have an error in method getVerifyIdMapOrderId: " + ex.getMessage());
            return Optional.of(new OrderMapVerification());
        }
    }
    private OrderMapVerification mapDataFromDbQueryResult(Object[] result){
        OrderMapVerification bo = new OrderMapVerification();
        bo.setId(((BigInteger) result[0]).longValue());
        bo.setOrder_id((String) result[1]);
        bo.setVerify_id((String) result[2]);
        bo.setVerify_status((Integer) result[3]);
        return bo;
    }
}
