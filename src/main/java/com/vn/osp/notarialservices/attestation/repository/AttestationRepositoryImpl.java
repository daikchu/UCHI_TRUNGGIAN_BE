package com.vn.osp.notarialservices.attestation.repository;

import com.vn.osp.notarialservices.attestation.domain.AttestationBO;
import com.vn.osp.notarialservices.questionHelp.domain.QuestionBO;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;


/**
 * Author by DaiCQ
 * Date: 2020/11/20
 * */
@Repository
@Transactional
public class AttestationRepositoryImpl implements AttestationRepository{
    private static final Logger LOG = LoggerFactory.getLogger(AttestationRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    @Override
    public Long count(String search) {
        long count = 0;
        try{
            String query ="";
            query = "Select count(bo.id) from AttestationBO bo where 1=1 "+search;
            count = (Long)entityManager.createQuery(query).getSingleResult();
            return count;

        }catch (Exception e){
            LOG.error("Have error in AttestationRepositoryImpl.count method :"+e.getMessage());
        }
        return Long.valueOf(0);
    }

    @Override
    public List<AttestationBO> page(int page, String search) {
        List<AttestationBO> list;
        int offset = Constants.NUMBER_PER_ROW*(page-1);
        int maxresult = Constants.NUMBER_PER_ROW;
        try{
            String query = "";
            query = "Select bo from AttestationBO bo where 1=1 "+search;
            list = entityManager.createQuery(query).setFirstResult(offset).setMaxResults(maxresult).getResultList();

            return list;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in AttestationRepositoryImpl.page method :"+e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<AttestationBO> search(String search) {
        List<AttestationBO> list;
        try{
            String query = "";
            query = "Select bo from AttestationBO bo where 1=1 "+search;
            list = entityManager.createQuery(query, AttestationBO.class).getResultList();

            return list;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in AttestationRepositoryImpl.search method :"+e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<AttestationBO> listByParentCode(String parent_code) {
        List<AttestationBO> list;
        try{
            String query = "";
            query = "Select bo from AttestationBO bo where bo.parent_code = :parent_code";
            list = entityManager.createQuery(query, AttestationBO.class).setParameter("parent_code",parent_code).getResultList();

            return list;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in AttestationRepositoryImpl.search method :"+e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public AttestationBO get(Long id) {
        try{
            String query = "";
            AttestationBO item = entityManager.find(AttestationBO.class,id);
            return item;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in AttestationRepositoryImpl.get method :"+e.getMessage());
        }
        return new AttestationBO();
    }

    @Override
    public Boolean add(AttestationBO item) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        Long id = 0l;
        try{
            entityManagerCurrent.getTransaction().begin();
            entityManagerCurrent.persist(item);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
            id =item.getId();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in AttestationRepositoryImpl.add method :"+e.getMessage());
        }finally {
            entityManagerCurrent.close();
        }
        return false;
    }

    @Override
    public Boolean update(AttestationBO item) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        Long id = 0l;
        try{
            entityManagerCurrent.getTransaction().begin();
            entityManagerCurrent.merge(item);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
            id =item.getId();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in AttestationRepositoryImpl.update method :"+e.getMessage());
        }finally {
            if(entityManagerCurrent!=null)
                entityManagerCurrent.close();
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        try{
            String query = "";
            entityManagerCurrent.getTransaction().begin();

            AttestationBO item = entityManagerCurrent.find(AttestationBO.class,id);
            entityManagerCurrent.remove(item);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
        }catch (Exception e){

            e.printStackTrace();
            LOG.error(" Have error in AttestationRepositoryImpl.delete method :"+e.getMessage());
            return Boolean.valueOf(false);
        }finally {
            entityManagerCurrent.close();
        }
        return Boolean.valueOf(true);
    }
}
