package com.vn.osp.notarialservices.citizenVerificationOrder.repository;

import com.vn.osp.notarialservices.citizenVerificationNumber.domain.CitizenVerifyNumberBO;
import com.vn.osp.notarialservices.citizenVerificationOrder.domain.CitizenVerifyOrderBO;
import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderDTO;
import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderSum;
import com.vn.osp.notarialservices.citizenVerifyPackage.domain.CitizenVerifyPackageBO;
import com.vn.osp.notarialservices.paymentTransaction.dto.PaymentTransactionDTO;
import com.vn.osp.notarialservices.questionHelp.domain.QuestionBO;
import com.vn.osp.notarialservices.questionHelp.repository.QuestionRepositoryImpl;
import com.vn.osp.notarialservices.utils.TimeUtil;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
@Transactional
public class CitizenVerificationOrderRepositoryImpl implements CitizenVerificationOrderRepository{
    private static final Logger LOG = LoggerFactory.getLogger(CitizenVerificationOrderRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    @Override
    public Optional<CitizenVerifyOrderBO> insert(CitizenVerifyOrderBO citizenVerifyOrderBO) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        try{
            entityManagerCurrent.getTransaction().begin();
            entityManagerCurrent.persist(citizenVerifyOrderBO);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
            return Optional.of(citizenVerifyOrderBO);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in CitizenVerificationOrderRepositoryImpl.insert method :"+e.getMessage());
            throw e;
        }finally {
            if(entityManagerCurrent!=null)
                entityManagerCurrent.close();
        }
    }

    @Override
    public Optional<CitizenVerifyOrderBO> update(CitizenVerifyOrderBO citizenVerifyOrderBO) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        try{
            entityManagerCurrent.getTransaction().begin();
            entityManagerCurrent.merge(citizenVerifyOrderBO);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
            return Optional.of(citizenVerifyOrderBO);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in update.ContractFeeRepositoryImpl method :"+e.getMessage());
            throw e;
        }finally {
            if(entityManagerCurrent!=null)
                entityManagerCurrent.close();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long order_id) {
        return Optional.empty();
    }

    @Override
    public Optional<CitizenVerifyOrderBO> get(String order_id) {
        try{
            CitizenVerifyOrderBO bo = entityManager.createQuery("select bo from CitizenVerifyOrderBO bo where order_id = '" + order_id + "'", CitizenVerifyOrderBO.class).getSingleResult();
//            CitizenVerifyOrderBO bo =  entityManager.find(CitizenVerifyOrderBO.class,order_id);
            return Optional.of(bo);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<CitizenVerifyOrderDTO> getDetail(String order_id) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        String queryStr = buildQueryColumnsForDetail() + buildQueryFromForDetail(order_id) + " where orders.order_id = '"+order_id+"'";
        LOG.info(queryStr);
        try{
            Query query = entityManagerCurrent.createNativeQuery(queryStr);
            Object[] result = (Object[]) query.getSingleResult();
            CitizenVerifyOrderDTO dto = mapDataFromDbQueryResult(result);
            return Optional.of(dto);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in getDetail.ContractFeeRepositoryImpl method :"+e.getMessage());
            throw e;
        }finally {
            if(entityManagerCurrent!=null)
                entityManagerCurrent.close();
        }
    }

    @Override
    public Optional<List<CitizenVerifyOrderDTO>> filter(String order_id, String notary_office_code, String province_code,
                                                        String transaction_status, String status, String update_by,
                                                        String order_time_from, String order_time_to, String update_user_name) {
        String filter = buildFilter(order_id, notary_office_code, province_code, transaction_status, status,
                update_by,order_time_from, order_time_to , update_user_name);
        String queryStr = buildQueryColumns() + buildQueryFrom() + " where 1=1 " + filter + " order by orders.order_time desc";


        Query query = entityManager.createNativeQuery(queryStr);
        List<Object[]> queryResultList = query.getResultList();
        List<CitizenVerifyOrderDTO> results = new ArrayList<>();
        for (Object[] result : queryResultList) {
            CitizenVerifyOrderDTO dto = mapDataFromDbQueryResult(result);
            results.add(dto);
        }

        return Optional.of(results);
    }

    @Override
    public Optional<List<CitizenVerifyOrderDTO>> page(int page, int numberPerPage, String order_id, String notary_office_code,
                                                      String province_code, String transaction_status, String status,
                                                      String update_by_officer, String order_time_from, String order_time_to, String update_user_name) {
        int offset = numberPerPage*(page-1);
        try{

            String filter = buildFilter(order_id, notary_office_code, province_code, transaction_status, status,
                    update_by_officer,order_time_from, order_time_to , update_user_name);

            String queryStr = buildQueryColumns() + buildQueryFrom() + " where 1=1 " + filter + " order by orders.order_time desc";
            LOG.info(queryStr);
            Query query = entityManager.createNativeQuery(queryStr);
            query.setFirstResult(offset);
            query.setMaxResults(numberPerPage);
            List<Object[]> queryResultList = query.getResultList();
            List<CitizenVerifyOrderDTO> results = new ArrayList<>();
            for (Object[] result : queryResultList) {
                CitizenVerifyOrderDTO dto = mapDataFromDbQueryResult(result);
                results.add(dto);
            }
            return Optional.of(results);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in page.CitizenVerificationOrderRepositoryImpl method :"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Long> count(String order_id, String notary_office_code, String province_code,
                                String transaction_status, String status, String update_by,
                                String order_time_from, String order_time_to, String update_user_name) {
        String filter = buildFilter(order_id, notary_office_code, province_code, transaction_status,
                status, update_by,order_time_from, order_time_to, update_user_name);
        long count = 0;
        try{
            String query = "Select count(DISTINCT orders.order_id) " + buildQueryFrom() + " where 1=1 " + filter;
            LOG.info(query);
            BigInteger countBigInt = (BigInteger)entityManager.createNativeQuery(query).getSingleResult();
            count = countBigInt.longValue();
            return Optional.of(count);

        }catch (Exception e){
            LOG.error("Have error in count.CitizenVerificationOrderRepositoryImpl method :"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<CitizenVerifyOrderBO>> getCitizenVerifyOrderByFilter(String stringFilter){
        List<CitizenVerifyOrderBO> results = new ArrayList<>();
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        entityManagerCurrent.getTransaction().begin();
        try {
            String hql = "select cvo from CitizenVerifyOrderBO cvo " + stringFilter;
            results = entityManagerCurrent.createQuery(hql).getResultList();
            entityManagerCurrent.close();
        } catch (Exception e) {
            LOG.error("Have an error in method CitizenVerificationOrderRepositoryImpl.getCitizenVerifyNumberByFilter: " + e.getMessage());
            entityManagerCurrent.getTransaction().rollback();
            entityManagerCurrent.close();
            return Optional.of(new ArrayList<>());
        }
        return Optional.of(results);
    }

    @Override
    public Optional<Map> getSumCitizenVerifyOrder(String order_id, String notary_office_code, String province_code, String transaction_status, String status, String update_by, String order_time_from, String order_time_to, String update_user_name) {
        String filter = buildFilter(order_id, notary_office_code, province_code, transaction_status,
                status, update_by,order_time_from, order_time_to, update_user_name);
        String queryStr = buildQueryColumns() + buildQueryFrom() + " where 1=1 " + filter;

        try{
            String queryStrSum = "with cte as (" + queryStr + ") select sum(verify_number) as verify_number, sum(verify_fee) as verify_fee, sum(verify_fee_received) as verify_fee_received from cte ";
            LOG.info(queryStr);
            Query query = entityManager.createNativeQuery(queryStrSum);
            Object[] result = (Object[]) query.getSingleResult();
            Map citizenVerifyOrderSum = new HashMap<>();
            citizenVerifyOrderSum.put("verify_number", result[0]);
            citizenVerifyOrderSum.put("verify_fee", result[1]);
            citizenVerifyOrderSum.put("verify_fee_received", result[2]);
            return Optional.of(citizenVerifyOrderSum);

        }catch (Exception e){
            LOG.error("Have error in getSumCitizenVerifyOrder.CitizenVerificationOrderRepositoryImpl method :"+e.getMessage());
        }
        return Optional.empty();

    }

    @Override
    public Optional<List<Map>> retrieveUserAccountsFromData(String province_code, String notary_office_code) {
        String sql = "select distinct update_by as account, update_by_name as name from npo_citizen_verification_orders where 1=1";
        if(!StringUtils.isBlank(province_code)){
            sql += " and province_code = '"+province_code+"'";
        }
        if(!StringUtils.isBlank(notary_office_code)){
            sql += " and notary_office_code = '"+notary_office_code+"'";
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

    private String buildQueryColumns() {
        return "select" +
                " distinct(orders.order_id)" +
                ", orders.order_time" +
                ", orders.verify_number" +
                ", orders.verify_fee" +
                ", orders.verify_fee_received" +
                ", orders.notary_office_code" +
                ", office.name as notary_office_name" +
                ", orders.province_code" +
                ", province.name as province_name" +
                ", orders.transaction_status" +
                ", transactionStatus.name as transaction_status_name" +
                ", orders.status" +
                ", statusAdd.name as status_name " +
                ", orders.update_by" +
                ", orders.update_by_name" +
                ", orders.note" +
                ", orders.update_user_name" +
                ", CASE orders.update_user_name WHEN 'system' then 'Hệ thống'" +
                " ELSE CONCAT(users.family_name, ' ', users.first_name) END AS update_by_user_fullname" +
                ", DATE_FORMAT(orders.order_time, '%d/%m/%Y %H:%i:%s') AS order_time_formatted" +
                ", orders.payment_content";
    }
    private String buildQueryColumnsForDetail() {
        return "select" +
                " distinct(orders.order_id)" +
                ", orders.order_time" +
                ", orders.verify_number" +
                ", orders.verify_fee" +
                ", orders.verify_fee_received" +
                ", orders.notary_office_code" +
                ", office.name as notary_office_name" +
                ", orders.province_code" +
                ", province.name as province_name" +
                ", orders.transaction_status" +
                ", transactionStatus.name as transaction_status_name" +
                ", orders.status" +
                ", statusAdd.name as status_name " +
                ", orders.update_by" +
                ", orders.update_by_name" +
                ", orders.note" +
                ", orders.update_user_name" +
                ", CASE orders.update_user_name WHEN 'system' then 'Hệ thống'" +
                " ELSE CONCAT(users.family_name, ' ', users.first_name) END AS update_by_user_fullname" +
                ", DATE_FORMAT(orders.order_time, '%d/%m/%Y %H:%i:%s') AS order_time_formatted" +
                ", orders.payment_content";
    }
    private String buildQueryFromForDetail(String order_id) {
        return " from npo_citizen_verification_orders orders " +
                " left join npo_province province on province.code = orders.province_code " +
                " left join npo_customer office on office.code = orders.notary_office_code " +
                " left join npo_status transactionStatus on transactionStatus.code = orders.transaction_status " +
                " left join npo_status statusAdd on statusAdd.code = orders.status " +
                " left join npo_user users on users.account = orders.update_user_name ";
    }

    private String buildQueryFrom() {
        return " from npo_citizen_verification_orders orders " +
                " left join npo_province province on province.code = orders.province_code " +
                " left join npo_customer office on office.code = orders.notary_office_code " +
                " left join npo_status transactionStatus on transactionStatus.code = orders.transaction_status " +
                " left join npo_status statusAdd on statusAdd.code = orders.status " +
                " left join npo_user users on users.account = orders.update_user_name ";
    }
    private String buildFilter(String order_id, String notary_office_code, String province_code,
                               String transaction_status, String status, String update_by_officer,
                               String order_time_from, String order_time_to, String update_user_name){

        StringBuilder query = new StringBuilder();
        if(StringUtils.isNotBlank(order_id)){
            query.append(" and orders.order_id like '%").append(order_id).append("%'");
        }
        if(StringUtils.isNotBlank(notary_office_code)){
            query.append(" and orders.notary_office_code = '").append(notary_office_code).append("'");
        }
        if(StringUtils.isNotBlank(province_code)){
            query.append(" and orders.province_code = '").append(province_code).append("'");
        }
        if(StringUtils.isNotBlank(transaction_status)){
            query.append(" and orders.transaction_status = '").append(transaction_status).append("'");
        }
        if(StringUtils.isNotBlank(status)){
            query.append(" and orders.status = '").append(status).append("'");
        }
        if(StringUtils.isNotBlank(update_by_officer)){
            query.append(" and orders.update_by = '").append(update_by_officer).append("'");
        }
        if(StringUtils.isNotBlank(order_time_from)){
            query.append(" and orders.order_time >= '").append(TimeUtil.toTimestamp(true, order_time_from)).append("'");
        }
        if(StringUtils.isNotBlank(order_time_to)){
            query.append(" and orders.order_time <= '").append(TimeUtil.toTimestamp(false, order_time_to)).append("'");
        }
        if(StringUtils.isNotBlank(update_user_name)){
            query.append(" and orders.update_user_name = '").append(update_user_name).append("'");
        }

        return query.toString();
    }

    private CitizenVerifyOrderDTO mapDataFromDbQueryResult(Object[] result){
        CitizenVerifyOrderDTO dto = new CitizenVerifyOrderDTO();
        dto.setOrder_id((String) result[0]);
        dto.setOrder_time((Timestamp) result[1]);
        dto.setVerify_number((int) result[2]);
        dto.setVerify_fee((String) result[3]);
        dto.setVerify_fee_received((String) result[4]);
        dto.setNotary_office_code((String) result[5]);
        dto.setNotary_office_name((String) result[6]);
        dto.setProvince_code((String) result[7]);
        dto.setProvince_name((String) result[8]);
        dto.setTransaction_status((String) result[9]);
        dto.setTransaction_status_name((String) result[10]);
        dto.setStatus((String) result[11]);
        dto.setStatus_name((String) result[12]);
        dto.setUpdate_by((String) result[13]);
        dto.setUpdate_by_name((String) result[14]);
        dto.setNote((String) result[15]);
        dto.setUpdate_user_name((String) result[16]);
        dto.setUpdate_user_fullname((String) result[17]);
        dto.setOrder_time_formatted((String) result[18]);
        dto.setPayment_content((String) result[19]);

        return dto;
    }
}
