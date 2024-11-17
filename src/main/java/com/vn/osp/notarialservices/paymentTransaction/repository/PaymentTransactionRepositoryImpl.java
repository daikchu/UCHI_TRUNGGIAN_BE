package com.vn.osp.notarialservices.paymentTransaction.repository;

import com.vn.osp.notarialservices.file.domain.AttachFiles;
import com.vn.osp.notarialservices.file.service.AttachFileService;
import com.vn.osp.notarialservices.paymentTransaction.domain.PaymentTransactions;
import com.vn.osp.notarialservices.paymentTransaction.dto.PaymentTransactionDTO;
import com.vn.osp.notarialservices.utils.TimeUtil;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PaymentTransactionRepositoryImpl implements PaymentTransactionRepositoryCustom{
    private static final Logger LOG = LoggerFactory.getLogger(PaymentTransactionRepositoryImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    AttachFileService attachFileService;


    @Override
    public Optional<PaymentTransactions> insert(PaymentTransactions paymentTransactions) {
        return Optional.empty();
    }

    @Override
    public Optional<PaymentTransactions> update(PaymentTransactions paymentTransactions) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        try{
            entityManagerCurrent.getTransaction().begin();
            entityManagerCurrent.merge(paymentTransactions);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
            return Optional.of(paymentTransactions);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in update.PaymentTransactionRepositoryImpl method :"+e.getMessage());
            throw e;
        }finally {
            if(entityManagerCurrent!=null)
                entityManagerCurrent.close();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long transaction_id) {
        return Optional.empty();
    }

    @Override
    public Optional<PaymentTransactionDTO> getDetail(String transaction_id) {
        String queryStr = buildQueryColumns() + buildQueryFrom() + " where payment.transaction_id = '"+transaction_id+"'";
        LOG.info(queryStr);
        Query query = entityManager.createNativeQuery(queryStr);
        Object[] result = (Object[]) query.getSingleResult();
        PaymentTransactionDTO dto = mapDataFromDbQueryResult(result);
        return Optional.of(dto);
    }

    @Override
    public Optional<PaymentTransactionDTO> getDetailById(Long id) {
        String queryStr = buildQueryColumns() + buildQueryFrom() + " where payment.id = "+id;
        LOG.info(queryStr);
        Query query = entityManager.createNativeQuery(queryStr);
        Object[] result = (Object[]) query.getSingleResult();
        PaymentTransactionDTO dto = mapDataFromDbQueryResult(result);
        return Optional.of(dto);
    }

    @Override
    public Optional<List<PaymentTransactionDTO>> filter(String order_id) {
        String queryStr = buildQueryColumns() + buildQueryFrom() + buildQueryWhere(order_id) + " order by payment.transaction_time desc";
        LOG.info(queryStr);
        Query query = entityManager.createNativeQuery(queryStr);
        List<Object[]> queryResultList = query.getResultList();
        List<PaymentTransactionDTO> results = new ArrayList<>();
        for (Object[] result : queryResultList) {
            PaymentTransactionDTO dto = mapDataFromDbQueryResult(result);
            //todo query get all file của mỗi transaction, bản chất 1 order có ít transactions nên có thể thực hiện bằng cách này
            List<AttachFiles> attachFileNames = attachFileService.getByCodeAndType(String.valueOf(dto.getId()), Constants.ATTACH_FILE_CODE.PAYMENT_TRANSACTION.getValue());
            dto.setAttach_files(attachFileNames);
            results.add(dto);
        }

        return Optional.of(results);
    }

    @Override
    public Optional<List<PaymentTransactionDTO>> page(String order_id) {
        return Optional.empty();
    }

    @Override
    public Optional<Long> count(String order_id) {
        return Optional.empty();
    }

    private String buildQueryColumns() {
        return "select " +
                " distinct(payment.id)" +
                ", payment.transaction_id" +
                ", payment.order_id" +
                ", payment.transaction_time" +
                ", payment.reference_number" +
                ", payment.amount" +
                ", payment.content" +
                ", payment.bank_account" +
                ", payment.trans_type" +
                ", payment.error" +
                ", payment.error_reason" +
                ", payment.toast_message" +
                ", payment.ref_transaction_id" +
                ", payment.transaction_status" +
                ", payment.status" +
                ", office.name as notary_office_name" +
                ", province.name as province_name" +
                ", orders.update_by_name" +
                ", payment.note" +
                ", transactionStatus.name as transaction_status_name" +
                ", statusAdd.name as status_name" +
                ", DATE_FORMAT(payment.update_date_time, '%d/%m/%Y %H:%i:%s') AS update_time_formatted" +
                ", CASE payment.update_user_name WHEN 'system' then 'Hệ thống'" +
                " ELSE CONCAT(users.family_name, ' ', users.first_name) END AS update_by_user_fullname" +
                ", orders.verify_number" +
                ", orders.verify_fee";
    }

    private String buildQueryFrom() {
        return " from npo_payment_transactions payment" +
                " join npo_citizen_verification_orders orders" +
                " on payment.order_id = orders.order_id" +
                " left join npo_province province" +
                " on orders.province_code = province.code" +
                " left join npo_customer office" +
                " on orders.notary_office_code = office.code" +
                " left join npo_status transactionStatus" +
                " on transactionStatus.code = payment.transaction_status" +
                " left join npo_status statusAdd" +
                " on statusAdd.code = payment.status" +
                " left join npo_user users on users.account = payment.update_user_name ";
    }

    private String buildQueryWhere(String order_id) {
        StringBuilder whereStr = new StringBuilder(" where 1=1 ");
        if(StringUtils.isNotBlank(order_id)) {
            whereStr.append(" and payment.order_id = '").append(order_id).append("'");
        }
        return whereStr.toString();

    }

    private PaymentTransactionDTO mapDataFromDbQueryResult(Object[] result){
        PaymentTransactionDTO dto = new PaymentTransactionDTO();
        dto.setId(((BigInteger) result[0]).longValue());
        dto.setTransaction_id((String) result[1]);
        dto.setOrder_id((String) result[2]);
        dto.setTransaction_time((String) result[3]);
        dto.setReference_number((String) result[4]);
        dto.setAmount((String) result[5]);
        dto.setContent((String) result[6]);
        dto.setBank_account((String) result[7]);
        dto.setTrans_type((String) result[8]);
        dto.setError((int) result[9]);
        dto.setError_reason((String) result[10]);
        dto.setToast_message((String) result[11]);
        dto.setRef_transaction_id((String) result[12]);
        dto.setTransaction_status((String) result[13]);
        dto.setStatus((String) result[14]);
        dto.setNotary_office_name((String) result[15]);
        dto.setProvince_name((String) result[16]);
        dto.setUpdate_by_name((String) result[17]);
        dto.setNote((String) result[18]);
        dto.setTransaction_status_name((String) result[19]);
        dto.setStatus_name((String) result[20]);
        dto.setTransaction_time_formatted((String) result[21]);
        dto.setUpdate_user_fullname((String) result[22]);
        dto.setVerify_number((int) result[23]);
        dto.setVerify_fee((String) result[24]);

        return dto;
    }
    @Override
    public Optional<PaymentTransactions> saveOrUpdateTransactionBO(PaymentTransactions paymentTransactions){
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        try {
            entityManagerCurrent.getTransaction().begin();
            entityManagerCurrent.persist(paymentTransactions);
            entityManagerCurrent.getTransaction().commit();
        }catch (Exception e){
            LOG.error("Have an error in method saveOrUpdateTransactionBO: "+e.getMessage());
            return Optional.of(new PaymentTransactions());
        }finally {
            entityManagerCurrent.clear();
            entityManagerCurrent.close();
        }
        return Optional.of(paymentTransactions);
    }
    @Override
    public Boolean checkExitsRefTransactionId(String refTransactionId) {
        Boolean exitsTransactionId = false;
        Long result = 0L;
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        entityManagerCurrent.getTransaction().begin();
        try {
            String hql = "select count(pt.ref_transaction_id) from PaymentTransactions pt where pt.ref_transaction_id = '"+refTransactionId+"'";
            result = (Long) entityManagerCurrent.createQuery(hql).getSingleResult();
            if (result > 0L) {
                exitsTransactionId = true;
            }
            entityManagerCurrent.close();
        } catch (Exception e) {
            LOG.error("Have an error in method checkExitsRefTransactionId: " + e.getMessage());
            entityManagerCurrent.getTransaction().rollback();
            return exitsTransactionId;
        } finally {
            entityManagerCurrent.close();
        }
        return exitsTransactionId;
    }

    @Override
    public Optional<PaymentTransactions> getPaymentTransaction(String stringFilter) {
        PaymentTransactions result = new PaymentTransactions();
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        entityManagerCurrent.getTransaction().begin();
        try {
            String hql = "select pt from PaymentTransactions pt " + stringFilter;
            LOG.info(hql);
            List<PaymentTransactions> resultList = entityManagerCurrent.createQuery(hql).getResultList();
            if (!resultList.isEmpty()) {
                result = resultList.get(0);
            }
            entityManagerCurrent.close();
        } catch (Exception e) {
            LOG.error("Have an error in method getPaymentTransaction: " + e.getMessage());
            entityManagerCurrent.getTransaction().rollback();
            entityManagerCurrent.close();
            return Optional.of(new PaymentTransactions());
        } finally {
            entityManagerCurrent.close();
        }
        return Optional.of(result);
    }
    @Override
    public Optional<List<PaymentTransactions>> updateStatusOrderIdAfter15Minutes(){
        List<PaymentTransactions> result = new ArrayList<>();
        /*EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        entityManagerCurrent.getTransaction().begin();*/
        try{
            StringBuilder hql = new StringBuilder();
            hql.append("SELECT pt.* FROM");
            hql.append(" (SELECT pay_tran.* FROM npo_payment_transactions pay_tran");
            hql.append(" WHERE TIMESTAMPDIFF(SECOND, pay_tran.update_date_time, NOW()) >= 900");
            hql.append(" AND transaction_status = 'pending') pt");
            hql.append(" WHERE pt.ORDER_ID not in");
            hql.append(" (select p.ORDER_ID from npo_payment_transactions p");
            hql.append(" where p.transaction_status IN ('fail','success') and p.ORDER_ID is not null");
            hql.append(" GROUP BY p.ORDER_ID)");
            LOG.info(hql.toString());
            Query query = entityManager.createNativeQuery(hql.toString());
            List<Object[]> list = query.getResultList();
            if(!list.isEmpty()){
                result = mapObjectToBo(list);
                if(!result.isEmpty()){
                    for(int i = 0; i < result.size(); i++){
                        entityManager.persist(result.get(i));
//                        if (i % 20 == 0) { // Insert in batches of 20 (adjust as needed)
//                            entityManager.flush();
//                            entityManager.clear();
//                        }
                    }
                    entityManager.flush();
                    entityManager.clear();
                }
            }
        }catch (Exception e) {
            LOG.error("Have an error in method updateStatusOrderIdAfter15Minutes: " + e.getMessage());
            return Optional.of(new ArrayList<>());
        }
        finally {
            entityManager.close();
            entityManager.flush();
            entityManager.clear();
        }
        return Optional.of(result);
    }
    private List<PaymentTransactions> mapObjectToBo(List<Object[]> list){
        List<PaymentTransactions> results = new ArrayList<>();
        for(Object[] o : list){
            PaymentTransactions pt = new PaymentTransactions();
            pt.setOrder_id((String) o[2]);
            pt.setAmount((String) o[5]);
            pt.setContent((String) o[6]);
            pt.setBank_account((String) o[7]);
            pt.setTrans_type((String) o[8]);
            pt.setTransaction_status(Constants.Status_PaymentTransaction.FAIL.getValue());
            pt.setEntry_date_time(TimeUtil.getTimeNow());
            pt.setEntry_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
            pt.setUpdate_date_time(TimeUtil.getTimeNow());
            pt.setUpdate_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
            results.add(pt);
        }
        return results;
    }
}
