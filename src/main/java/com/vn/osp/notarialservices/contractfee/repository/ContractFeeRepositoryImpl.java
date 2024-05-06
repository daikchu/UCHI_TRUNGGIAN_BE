package com.vn.osp.notarialservices.contractfee.repository;

import com.vn.osp.notarialservices.contractfee.domain.ContractFeeBO;
import com.vn.osp.notarialservices.contractfee.dto.ContractFeeCodeTemplate;
import com.vn.osp.notarialservices.contractkind.domain.ContractKindBo;
import com.vn.osp.notarialservices.contracttemplate.domain.ContractTempBo;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.util.Assert;
import sun.rmi.runtime.Log;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 2018-05-09.
 */
public class ContractFeeRepositoryImpl implements ContractFeeRepositoryCustom {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(ContractFeeRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired

    public ContractFeeRepositoryImpl(JpaContext context) {
        Assert.notNull(context, "JpaContext must not be null!");
    }
    @Override
    public Optional<Integer> addContractFee(ContractFeeBO contractFeeBO){
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        int intid = 0;
        try{
            entityManagerCurrent.getTransaction().begin();
            entityManagerCurrent.persist(contractFeeBO);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
            intid =contractFeeBO.getId();
            return Optional.of(intid);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in addContractFee.ContractFeeRepositoryImpl method :"+e.getMessage());
        }finally {
            entityManagerCurrent.close();
        }
        return Optional.of(Integer.valueOf(0));
    }

    @Override
    public Optional<Integer> updateContractFee(ContractFeeBO contractFeeBO){
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        int id = 0;
        try{
            entityManagerCurrent.getTransaction().begin();
            entityManagerCurrent.merge(contractFeeBO);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
            id =contractFeeBO.getId();
            return Optional.of(id);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in addContractFee.ContractFeeRepositoryImpl method :"+e.getMessage());
        }finally {
            if(entityManagerCurrent!=null)
            entityManagerCurrent.close();
        }
        return Optional.of(Integer.valueOf(0));
    }

    @Override
    public Optional<List<ContractFeeBO>> getContractFeeCode(int page,String codeContract){
//        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        List<ContractFeeBO> list= new ArrayList<>();
        int offset = Constants.NUMBER_PER_ROW*(page-1);
        int maxresult = Constants.NUMBER_PER_ROW;


        try{
            String query = "";
//            entityManagerCurrent.getTransaction().begin();
                if(codeContract==null || codeContract.equals("")){
                    query = "Select ncf from ContractFeeBO ncf ";
                    list = entityManager.createQuery(query).setFirstResult(offset).setMaxResults(maxresult).getResultList();
                }else {
                    Long codeLong = Long.valueOf(codeContract);
                    query = "Select ncf from ContractFeeBO ncf where ncf.ct_template_code = :codeContract ";
                    list = entityManager.createQuery(query).setParameter("codeContract",codeLong).setFirstResult(offset).setMaxResults(maxresult).getResultList();
                }
            return Optional.of(list);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in getContractFeeCode.ContractFeeRepository method :"+e.getMessage());
//        }finally {
//            entityManagerCurrent.close();
        }
        return Optional.of(new ArrayList<ContractFeeBO>());
    }

    @Override
    public Optional<List<ContractFeeBO>> getContractFeeAll(){
//        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        List<ContractFeeBO> list= new ArrayList<>();
        try{
            String query = "";
//            entityManager.getTransaction().begin();

                query = "Select ncf from ContractFeeBO ncf";
                list = entityManager.createQuery(query).getResultList();


            return Optional.of(list);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in getContractFeeCode.ContractFeeRepository method :"+e.getMessage());
//        }finally {
//            entityManagerCurrent.close();
        }
        return Optional.of(new ArrayList<ContractFeeBO>());
    }
    // get gợi ý theo id for update
    @Override
    public Optional <ContractFeeBO> getContractFeeId(int id){
//        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        try{
            String query = "";
//            entityManagerCurrent.getTransaction().begin();

            ContractFeeBO list = entityManager.find(ContractFeeBO.class,id);


            return Optional.of(list);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in getContractFeeCode.ContractFeeRepository method :"+e.getMessage());
//        }finally {
//            entityManagerCurrent.close();
        }
        return Optional.of(new ContractFeeBO());
    }

    @Override
    public Optional<Long> countContractFeeAll(String codeContract){
//        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        long count = 0;
        try{
//            entityManagerCurrent.getTransaction().begin();
            String query ="";
            if(codeContract == null || codeContract.equals("")){
                query = "Select count(ncf.id) from ContractFeeBO ncf";
                count = (Long)entityManager.createQuery(query).getSingleResult();
            }else {
                Long codeLong = Long.valueOf(codeContract);
                query= "select count(ncf.id) from ContractFeeBO ncf where ncf.ct_template_code=:ct_template_code";
                count = (Long)entityManager.createQuery(query).setParameter("ct_template_code",codeLong).getSingleResult();
            }



            return Optional.of(count);

        }catch (Exception e){
            LOG.error("Have error in countContractFeeAll.ContractFeeRepository method :"+e.getMessage());
//        }finally{
//            entityManagerCurrent.close();
        }
        return Optional.of(Long.valueOf(0));
    }
    // xóa
    @Override
    public Boolean deleteContractFee(int id){
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        try{
            String query = "";
            entityManagerCurrent.getTransaction().begin();

            ContractFeeBO list = entityManagerCurrent.find(ContractFeeBO.class,id);
            entityManagerCurrent.remove(list);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
        }catch (Exception e){

            e.printStackTrace();
            LOG.error(" Have error in getContractFeeCode.ContractFeeRepository method :"+e.getMessage());
            return Boolean.valueOf(false);
        }finally {
            entityManagerCurrent.close();
        }
        return Boolean.valueOf(true);
    }
    // only code
    @Override
    public Optional<List<ContractFeeBO>> getContractFeeOnlyCode(Long codeContract){
//        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        List<ContractFeeBO> list= new ArrayList<>();
        try{
            String query = "";
          //  entityManage.getTransaction().begin();
            query = "Select ncf from ContractFeeBO ncf where ncf.ct_template_code = :codeContract ";
            list = entityManager.createQuery(query).setParameter("codeContract",codeContract).getResultList();
            return Optional.of(list);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in getContractFeeCode.ContractFeeRepository method :"+e.getMessage());
//        }finally {
//            entityManagerCurrent.close();
        }
        return Optional.of(new ArrayList<ContractFeeBO>());
    }

    // only code except id

    @Override
    public Optional<List<ContractFeeBO>> getContractFeeOnlyCodeExceptId(Long codeContract,int id){
//        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        List<ContractFeeBO> list= new ArrayList<>();



        try{
            String query = "";
//            entityManagerCurrent.getTransaction().begin();
            query = "Select ncf from ContractFeeBO ncf where ncf.ct_template_code = :codeContract and ncf.id != :id ";
            list = entityManager.createQuery(query).setParameter("codeContract",codeContract).setParameter("id",id).getResultList();
            return Optional.of(list);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in getContractFeeCode.ContractFeeRepository method :"+e.getMessage());
//        }finally {
//            entityManagerCurrent.close();
        }
        return Optional.of(new ArrayList<ContractFeeBO>());
    }

    // fee tccc

    @Override
    public Optional<List<ContractFeeBO>> getContractFeeTcccCall(Long codeContract,long notaryCost){
//        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        List<ContractFeeBO> list= new ArrayList<>();



        try{
            String query = "";
//            entityManagerCurrent.getTransaction().begin();
            query = "Select ncf from ContractFeeBO ncf where ncf.ct_template_code = :codeContract and ncf.from_fee< :notaryCost and (ncf.to_fee >=:notaryCost or ncf.to_fee IS NULL) ";
            list = entityManager.createQuery(query).setParameter("codeContract",codeContract).setParameter("notaryCost",notaryCost).getResultList();
            return Optional.of(list);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in getContractFeeTcccCall.ContractFeeRepository method :"+e.getMessage());
//        }finally {
//            entityManagerCurrent.close();
        }
        return Optional.of(new ArrayList<ContractFeeBO>());
    }

    @Override
    public Optional<List<ContractFeeCodeTemplate>> getContractFeeCodeJoinContractTemplate(int page,String codeContract,String codeKind){
        List<ContractFeeCodeTemplate> list= new ArrayList<>();
//        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        int offset = Constants.NUMBER_PER_ROW*(page-1);
        int maxresult = Constants.NUMBER_PER_ROW;
        try{
//            entityManagerCurrent.getTransaction().begin();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Object[]> cquery = cb.createQuery(Object[].class);
            Root<ContractFeeBO> root = cquery.from(ContractFeeBO.class);
            Root<ContractTempBo> rootContractTemp = cquery.from(ContractTempBo.class);
            Root<ContractKindBo> rootContractKind = cquery.from(ContractKindBo.class);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("ct_template_code"),rootContractTemp.get("code_template")));
            predicates.add(cb.equal(rootContractTemp.get("code"),rootContractKind.get("contract_kind_code")));
            if(codeContract != null && !codeContract.equals("")){
                predicates.add(cb.equal(root.get("ct_template_code"),codeContract));
            }
            if(codeKind != null && !codeKind.equals("")){
                predicates.add(cb.equal(rootContractTemp.get("code"),codeKind));
            }

            cquery.multiselect(root.get("id"),root.get("ct_template_code"),root.get("from_fee"),root.get("to_fee"),root.get("formula_fee"),rootContractTemp.get("code"),rootContractTemp.get("name"),rootContractKind.get("name"));
            cquery.where(predicates.toArray(new Predicate[]{}));
            cquery.orderBy(cb.asc(root.get("ct_template_code")),cb.asc(root.get("from_fee")));
            List<Object[]> result = entityManager.createQuery(cquery).setFirstResult(offset).setMaxResults(maxresult).getResultList();
            result.stream().forEach((record)->{
                ContractFeeCodeTemplate contractFeeCodeTemplate = new ContractFeeCodeTemplate();
                contractFeeCodeTemplate.setId(record[0] == null ? null:(Integer) record[0]);
                contractFeeCodeTemplate.setCt_template_code(record[1]== null ? null:(Long) record[1]);
                contractFeeCodeTemplate.setFrom_fee(record[2] == null ? null :(Long) record[2]);
                contractFeeCodeTemplate.setTo_fee(record[3] == null ? null:(Long) record[3]);
                contractFeeCodeTemplate.setFormula_fee(record[4] == null? null:(String) record[4]);
                contractFeeCodeTemplate.setCode_kind(record[5]== null ? null:(String) record[5]);
                contractFeeCodeTemplate.setTemplate_name(record[6] == null ? null:(String) record[6]);
                contractFeeCodeTemplate.setKind_name(record[7] == null ? null:(String) record[7]);

                list.add(contractFeeCodeTemplate);

            });
            return Optional.of(list);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in getContractFeeCodeJoinContractTemplate.ContractFeeRepository method :"+e.getMessage());
//        }finally {
//            entityManagerCurrent.close();
        }
        return Optional.of(new ArrayList<ContractFeeCodeTemplate>());
    }

    @Override
    public Optional<Long> countContractFeeAllInnerJoinContractTemplate(String codeContract,String codeKind){
//        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();

        long count = 0;
        try{
//            entityManagerCurrent.getTransaction().begin();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> cquery = cb.createQuery(Long.class);
            Root<ContractFeeBO> root = cquery.from(ContractFeeBO.class);
            Root<ContractTempBo> rootContractTemp = cquery.from(ContractTempBo.class);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("ct_template_code"),rootContractTemp.get("code_template")));
            if(codeContract != null && !codeContract.equals("")){
                predicates.add(cb.equal(root.get("ct_template_code"),codeContract));
            }
            if(codeKind != null && !codeKind.equals("")){
                predicates.add(cb.equal(rootContractTemp.get("code"),codeKind));
            }
            cquery.select(cb.count(root.get("id")));
            cquery.where(predicates.toArray(new Predicate[]{}));

            count = entityManager.createQuery(cquery).getSingleResult();
            return Optional.of(count);

        }catch (Exception e){
            LOG.error("Have error in countContractFeeAll.ContractFeeRepository method :"+e.getMessage());
//        }finally{
//            entityManagerCurrent.close();
        }
        return Optional.of(Long.valueOf(0));
    }

    // test
    @Override
    public Optional<List<ContractFeeCodeTemplate>> getTest(){


        /*Join<ContractFeeBO,ContractTempBo> item = root.join("ContractFeeBO123", JoinType.INNER);
        item.on(cb.equal(root.get("ct_tem"),item.get("ContractFeeBO123").get("")));

        Root<Order> order = cq.from(Order.class);
        Join<Order, Item> item = order.join(Order_.itemList, JoinType.LEFT);
        item.on(cb.equal(item.get(Item_.type), 1));*/
//        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();

        try{
            List<ContractFeeCodeTemplate> contractFeeCodeTemplateList = new ArrayList<>();

//            entityManagerCurrent.getTransaction().begin();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Object[]> cquery = cb.createQuery(Object[].class);
            Root<ContractFeeBO> root = cquery.from(ContractFeeBO.class);
            Root<ContractTempBo> rootContractTemp = cquery.from(ContractTempBo.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(cb.equal(root.get("ct_template_code"),rootContractTemp.get("code_template")));
            cquery.multiselect(root.get("id"),root.get("ct_template_code"),root.get("from_fee"),root.get("to_fee"),root.get("formula_fee"),rootContractTemp.get("code"));
            cquery.where(predicates.toArray(new Predicate[]{}));
            List<Object[]> result = entityManager.createQuery(cquery).getResultList();
            result.stream().forEach((record) -> {
                ContractFeeCodeTemplate contractFeeCodeTemplate = new ContractFeeCodeTemplate();

                contractFeeCodeTemplate.setId(record[0] == null ? null : ((Integer) record[0]).intValue());
                contractFeeCodeTemplate.setCt_template_code(record[1] == null ? null : ((Long) record[1]));
                contractFeeCodeTemplate.setFrom_fee(record[2] == null ? null : ((Long) record[2]));
                contractFeeCodeTemplate.setTo_fee(record[3] == null ? null : ((Long) record[3]));
                contractFeeCodeTemplate.setFormula_fee(record[4] == null ? null : ((String) record[4]));
                contractFeeCodeTemplate.setCode_kind(record[5] == null ? null:((String) record[5]));


                contractFeeCodeTemplateList.add(contractFeeCodeTemplate);

            });
            return Optional.of(contractFeeCodeTemplateList);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in getContractFeeCode.ContractFeeRepository method :"+e.getMessage());
//        }finally {
//            entityManagerCurrent.close();
        }
        return Optional.of(new ArrayList<ContractFeeCodeTemplate>());
    }








}
