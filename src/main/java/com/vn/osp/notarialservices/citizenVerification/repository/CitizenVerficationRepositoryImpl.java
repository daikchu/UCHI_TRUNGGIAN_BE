package com.vn.osp.notarialservices.citizenVerification.repository;
import com.vn.osp.notarialservices.citizenVerification.domain.CitizenVerificationsBO;
import com.vn.osp.notarialservices.citizenVerification.dto.CitizenVerificationsDTO;
import com.vn.osp.notarialservices.utils.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

@Repository
@Transactional
public class CitizenVerficationRepositoryImpl implements CitizenVerficationRepository{
    private static final Logger LOG = LoggerFactory.getLogger(CitizenVerficationRepositoryImpl.class);
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<CitizenVerificationsBO> saveOrUpdate(CitizenVerificationsBO citizenVerificationsBO) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        try {
            entityManagerCurrent.getTransaction().begin();
            if(citizenVerificationsBO.getId() == null){
                entityManagerCurrent.persist(citizenVerificationsBO);
            }else {
                entityManagerCurrent.merge(citizenVerificationsBO);
            }
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Have an error in method CitizenInformationRepositoryImpl.saveOrUpdate: " + e.getMessage());
            throw e;
        } finally {
            entityManagerCurrent.clear();
            entityManagerCurrent.close();
        }
        return Optional.of(citizenVerificationsBO);
    }

    @Override
    public Optional<List<CitizenVerificationsDTO>> page(int page, int numberPerPage, String verify_id, String province_code,
                                                        String notary_office_id, String verify_status, String verify_by,
                                                        String citizen_verify_fullname, String citizen_verify_cccd,
                                                        String verify_date_from, String verify_date_to,
                                                        String order_id, String verify_by_name) {
        int offset = numberPerPage*(page-1);
        try {
            String filter = buildFilter(verify_id, province_code, notary_office_id, verify_status, verify_by,
                    citizen_verify_fullname, citizen_verify_cccd, verify_date_from, verify_date_to,
                    order_id, verify_by_name);
            String queryStr = buildQueryColumns(false) + buildQueryFrom() + " where 1=1 " + filter + " order by verification.verify_date desc";
            LOG.info(queryStr);

            Query query = entityManager.createNativeQuery(queryStr);
            query.setFirstResult(offset);
            query.setMaxResults(numberPerPage);
            List<Object[]> queryResultList = query.getResultList();
            List<CitizenVerificationsDTO> results = new ArrayList<>();
            for (Object[] result : queryResultList) {
                CitizenVerificationsDTO dto = mapDataFromDbQueryResult(result);
                results.add(dto);
            }
            return Optional.of(results);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in page.CitizenVerficationRepositoryImpl method :"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Long> count(String verify_id, String province_code, String notary_office_id,
                                String verify_status, String verify_by, String citizen_verify_fullname,
                                String citizen_verify_cccd, String verify_date_from, String verify_date_to,
                                String order_id, String verify_by_name) {
        String filter = buildFilter(verify_id, province_code, notary_office_id, verify_status, verify_by,
                citizen_verify_fullname, citizen_verify_cccd, verify_date_from, verify_date_to,
                order_id, verify_by_name);
        long count = 0;
        try{
            String query = "Select count(verification.verify_id) " + buildQueryFrom() + " where 1=1 " + filter;
            LOG.info(query);
            BigInteger countBigInt = (BigInteger)entityManager.createNativeQuery(query).getSingleResult();
            count = countBigInt.longValue();
            return Optional.of(count);

        }catch (Exception e){
            LOG.error("Have error in count.CitizenVerficationRepositoryImpl method :"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<CitizenVerificationsDTO> getDetail(String verify_id) {
        String queryStr = buildQueryColumns(true) + buildQueryFrom() + " where verification.verify_id = '"+verify_id+"'";
        LOG.info(queryStr);
        Query query = entityManager.createNativeQuery(queryStr);
        List<Object[]> queryResultList = query.getResultList();
        if(queryResultList != null && queryResultList.size() > 0) {
            CitizenVerificationsDTO dto = mapDataFromDbQueryResult(queryResultList.get(0));
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<CitizenVerificationsDTO>> filter(String verify_id, String province_code, String notary_office_id,
                                                          String verify_status, String verify_by, String citizen_verify_fullname,
                                                          String citizen_verify_cccd, String verify_date_from, String verify_date_to,
                                                          String order_id, String verify_by_name) {
        String filter = buildFilter(verify_id, province_code, notary_office_id, verify_status, verify_by,
                citizen_verify_fullname, citizen_verify_cccd, verify_date_from, verify_date_to,
                order_id, verify_by_name);
        String queryStr = buildQueryColumns(false) + buildQueryFrom() + " where 1=1 " + filter + " order by verification.verify_date desc";
        LOG.info(queryStr);

        Query query = entityManager.createNativeQuery(queryStr);
        List<Object[]> queryResultList = query.getResultList();
        List<CitizenVerificationsDTO> results = new ArrayList<>();
        for (Object[] result : queryResultList) {
            CitizenVerificationsDTO dto = mapDataFromDbQueryResult(result);
            results.add(dto);
        }
        return Optional.of(results);
    }

    @Override
    public Optional<List<Map>> retrieveUserAccountsFromData(String province_code, String notary_office_code) {
        String sql = "select distinct verify_by as account, verify_by_name as name from npo_citizen_verifications where 1=1";
        if(!StringUtils.isBlank(province_code)){
            sql += " and province_code = '"+province_code+"'";
        }
        if(!StringUtils.isBlank(notary_office_code)){
            sql += " and notary_office_id = '"+notary_office_code+"'";
        }
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> queryResultList = query.getResultList();
        List<Map> results = new ArrayList<>();
        for (Object[] result : queryResultList) {
            Map<String, String> map = new HashMap<>();
            map.put("account", (String) result[0]);
            map.put("name", (String) result[1]);
            results.add(map);
        }
        return Optional.of(results);
    }

    @Override
    public Optional<List<CitizenVerificationsDTO>> getListPurchased(String order_id) {
        String queryColumns = "SELECT orderVerify.verify_id, " +
                "verification.transaction_id, " +
                "verification.notary_office_id, " +
                "verification.province_code, " +
                "verification.verify_date, " +
                "verification.verify_by, " +
                "verification.verify_by_name, " +
                "verification.verify_status, " +
                "verification.citizen_verify_cccd, " +
                "verification.citizen_verify_fullname, " +
                "'' as citizen_info," +
                "province.name as province_name, " +
                "office.name as notary_office_name," +
                "CASE verification.verify_status WHEN 'success' THEN 'Thành công' WHEN 'fail' THEN 'Thất bại' ELSE '' END AS verify_status_name," +
                "DATE_FORMAT(verification.verify_date, '%d/%m/%Y %H:%i:%s') AS verify_date_formatted," +
                "orderVerify.order_id as order_id, " +
                "orderVerify.verify_status as using_status " +
                "FROM " +
                "npo_order_map_verification orderVerify " +
                "left join npo_citizen_verifications verification on orderVerify.verify_id = verification.verify_id " +
//                "JOIN npo_citizen_verification_orders orders on orderVerify.order_id = orders.order_id " +
                "left join npo_province province on province.code = verification.province_code " +
                "left join npo_customer office on office.code = verification.notary_office_id " +
                "where 1=1 ";
        if(StringUtils.isNotBlank(order_id)){
            queryColumns += " and orderVerify.order_id = '" + order_id + "'";
        }
        LOG.info("getListPurchased: " + queryColumns);

        try{
            Query query = entityManager.createNativeQuery(queryColumns);
            List<Object[]> queryResultList = query.getResultList();
            List<CitizenVerificationsDTO> results = new ArrayList<>();
            for (Object[] row : queryResultList) {
                CitizenVerificationsDTO citizenVerificationsDto = new CitizenVerificationsDTO();
                citizenVerificationsDto.setVerify_id((String) row[0]);
                citizenVerificationsDto.setTransaction_id((String) row[1]);
                citizenVerificationsDto.setNotary_office_id((String) row[2]);
                citizenVerificationsDto.setProvince_code((String) row[3]);
                citizenVerificationsDto.setVerify_date((Timestamp) row[4]);
                citizenVerificationsDto.setVerify_by((String) row[5]);
                citizenVerificationsDto.setVerify_by_name((String) row[6]);
                citizenVerificationsDto.setVerify_status((String) row[7]);
                citizenVerificationsDto.setCitizen_verify_cccd((String) row[8]);
                citizenVerificationsDto.setCitizen_verify_fullname((String) row[9]);
                citizenVerificationsDto.setCitizen_info((String) row[10]);
                citizenVerificationsDto.setProvince_name((String) row[11]);
                citizenVerificationsDto.setNotary_office_name((String) row[12]);
                citizenVerificationsDto.setVerify_status_name((String) row[13]);
                citizenVerificationsDto.setVerify_date_formatted((String) row[14]);
                citizenVerificationsDto.setOrder_id((String) row[15]);
                citizenVerificationsDto.setUsing_verify_status(((Integer) row[16]).intValue());

                results.add(citizenVerificationsDto);
            }
            return Optional.of(results);
        } catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in getListPurchased.CitizenVerficationRepositoryImpl method :"+e.getMessage());
        }
        return Optional.empty();

    }

    private CitizenVerificationsDTO mapDataFromDbQueryResult(Object[] row) {
        CitizenVerificationsDTO citizenVerificationsDto = new CitizenVerificationsDTO();
        citizenVerificationsDto.setVerify_id((String) row[0]);
        citizenVerificationsDto.setTransaction_id((String) row[1]);
        citizenVerificationsDto.setNotary_office_id((String) row[2]);
        citizenVerificationsDto.setProvince_code((String) row[3]);
        citizenVerificationsDto.setVerify_date((Timestamp) row[4]);
        citizenVerificationsDto.setVerify_by((String) row[5]);
        citizenVerificationsDto.setVerify_by_name((String) row[6]);
        citizenVerificationsDto.setVerify_status((String) row[7]);
        citizenVerificationsDto.setCitizen_verify_cccd((String) row[8]);
        citizenVerificationsDto.setCitizen_verify_fullname((String) row[9]);
        citizenVerificationsDto.setCitizen_info((String) row[10]);
        citizenVerificationsDto.setProvince_name((String) row[11]);
        citizenVerificationsDto.setNotary_office_name((String) row[12]);
        citizenVerificationsDto.setVerify_status_name((String) row[13]);
        citizenVerificationsDto.setVerify_date_formatted((String) row[14]);
        citizenVerificationsDto.setOrder_id((String) row[15]);
        return citizenVerificationsDto;
    }

    private String buildQueryColumns(boolean forDetail){
        return "SELECT verification.verify_id, " +
                "verification.transaction_id, " +
                "verification.notary_office_id, " +
                "verification.province_code, " +
                "verification.verify_date, " +
                "verification.verify_by, " +
                "verification.verify_by_name, " +
                "verification.verify_status, " +
                "verification.citizen_verify_cccd, " +
                "verification.citizen_verify_fullname, " +
                (forDetail ? "verification.citizen_info," : "'' as citizen_info,") +
                "province.name as province_name, " +
                "office.name as notary_office_name," +
                "CASE WHEN verification.verify_status = 'success' THEN 'Thành công' ELSE 'Thất bại' END AS verify_status_name," +
                "DATE_FORMAT(verification.verify_date, '%d/%m/%Y %H:%i:%s') AS verify_date_formatted," +
                "orderVerify.order_id as order_id ";
    }

    private String buildQueryFrom(){
        return "FROM npo_citizen_verifications verification " +
                "left join npo_province province on province.code = verification.province_code " +
                "join npo_customer office on office.code = verification.notary_office_id " +
                "left join npo_order_map_verification orderVerify on verification.verify_id = orderVerify.verify_id ";
    }
    private String buildFilter(String verify_id,
                                   String province_code,
                                   String notary_office_id,
                                   String verify_status,
                                   String verify_by,
                                   String citizen_verify_fullname,
                                   String citizen_verify_cccd,
                                   String verify_date_from,
                                   String verify_date_to,
                                    String order_id,
                               String verify_by_name){
        StringBuilder query = new StringBuilder();
        if(StringUtils.isNotBlank(verify_id)){
            query.append(" and verification.verify_id like '%").append(verify_id).append("%'");
        }
        if(StringUtils.isNotBlank(order_id)){
            query.append(" and orderVerify.order_id like '%").append(order_id).append("%'");
        }
        if(StringUtils.isNotBlank(province_code)){
            query.append(" and verification.province_code = '").append(province_code).append("'");
        }
        if(StringUtils.isNotBlank(notary_office_id)){
            query.append(" and verification.notary_office_id = '").append(notary_office_id).append("'");
        }
        if(StringUtils.isNotBlank(verify_status)){
            query.append(" and verification.verify_status = '").append(verify_status).append("'");
        }
        if(StringUtils.isNotBlank(verify_by)){
            query.append(" and verification.verify_by = '").append(verify_by).append("'");

        }
        if(StringUtils.isNotBlank(verify_by_name)){
            query.append(" and verification.verify_by like '%").append(verify_by_name).append("%'").append(" or verification.verify_by_name like '%").append(verify_by_name).append("%'");

        }
        if(StringUtils.isNotBlank(citizen_verify_fullname)){
            query.append(" and verification.citizen_verify_fullname like '%").append(citizen_verify_fullname).append("%'");
        }
        if(StringUtils.isNotBlank(citizen_verify_cccd)){
            query.append(" and verification.citizen_verify_cccd like '%").append(citizen_verify_cccd).append("%'");
        }
        if(StringUtils.isNotBlank(verify_date_from)){
            query.append(" and verification.verify_date >= '").append(TimeUtil.toTimestamp(true, verify_date_from)).append("'");
        }
        if(StringUtils.isNotBlank(verify_date_to)){
            query.append(" and verification.verify_date <= '").append(TimeUtil.toTimestamp(false, verify_date_to)).append("'");
        }
        return query.toString();
    }
}
