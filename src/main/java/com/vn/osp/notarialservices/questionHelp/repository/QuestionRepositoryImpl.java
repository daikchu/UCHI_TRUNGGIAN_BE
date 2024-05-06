/*
 * Copyright 2008-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vn.osp.notarialservices.questionHelp.repository;

//import com.vn.osp.notarialservices.user.domain.UserBO;

import com.vn.osp.notarialservices.questionHelp.domain.QuestionBO;
import com.vn.osp.notarialservices.questionHelp.dto.Question;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Dummy implementation to allow check for invoking a custom implementation.
 *
 * @author manhtran on 27/10/2016
 */
@Repository
@Transactional
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Long count(String search) {
        LOG.error("Có lỗi xảy ra khi thao tác csdl tịa hàm selectByFilter");
        long count = 0;
        try{
            String query ="";
                query = "Select count(bo.id) from QuestionBO bo where 1=1 "+search;
                count = (Long)entityManager.createQuery(query).getSingleResult();
            return count;

        }catch (Exception e){
            LOG.error("Have error in countContractFeeAll.ContractFeeRepository method :"+e.getMessage());
        }
        return Long.valueOf(0);
    }

    @Override
    public List<QuestionBO> page(int page,String search) {
        List<QuestionBO> list;
        int offset = Constants.NUMBER_PER_ROW*(page-1);
        int maxresult = Constants.NUMBER_PER_ROW;
        try{
            String query = "";
                query = "Select bo from QuestionBO bo where 1=1 "+search;
                list = entityManager.createQuery(query).setFirstResult(offset).setMaxResults(maxresult).getResultList();

            return list;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in page.QuestionRepositoryImpl method :"+e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<QuestionBO> search(String search) {
        List<QuestionBO> list;
        try{
            String query = "";
            query = "Select bo.* from npo_question_help bo where 1=1 "+search;
            list = entityManager.createNativeQuery(query, QuestionBO.class).getResultList();

            return list;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in search.QuestionRepositoryImpl method :"+e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public QuestionBO get(Long id) {
        try{
            String query = "";
            QuestionBO item = entityManager.find(QuestionBO.class,id);
            return item;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in get.QuestionRepositoryImpl method :"+e.getMessage());
        }
        return new QuestionBO();
    }

    @Override
    public Long add(QuestionBO item) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        Long id = 0l;
        try{
            entityManagerCurrent.getTransaction().begin();
            entityManagerCurrent.persist(item);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
            id =item.getId();
            return id;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in add.QuestionRepositoryImpl method :"+e.getMessage());
        }finally {
            entityManagerCurrent.close();
        }
        return 0l;
    }

    @Override
    public Long update(QuestionBO item) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        Long id = 0l;
        try{
            entityManagerCurrent.getTransaction().begin();
            entityManagerCurrent.merge(item);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
            id =item.getId();
            return id;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(" Have error in update.QuestionRepositoryImpl method :"+e.getMessage());
        }finally {
            if(entityManagerCurrent!=null)
                entityManagerCurrent.close();
        }
        return 0l;
    }

    @Override
    public Boolean delete(Long id) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        try{
            String query = "";
            entityManagerCurrent.getTransaction().begin();

            QuestionBO item = entityManagerCurrent.find(QuestionBO.class,id);
            entityManagerCurrent.remove(item);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
        }catch (Exception e){

            e.printStackTrace();
            LOG.error(" Have error in delete.QuestionRepositoryImpl method :"+e.getMessage());
            return Boolean.valueOf(false);
        }finally {
            entityManagerCurrent.close();
        }
        return Boolean.valueOf(true);
    }
}
