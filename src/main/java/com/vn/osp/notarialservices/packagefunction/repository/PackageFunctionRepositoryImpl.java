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
package com.vn.osp.notarialservices.packagefunction.repository;
import com.vn.osp.notarialservices.packagefunction.domain.PackageFunctionBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author quyenlc on 29/05/2021
 */
public class PackageFunctionRepositoryImpl implements PackageFunctionRepositoryCustom {

    private static final Logger LOG = LoggerFactory.getLogger(PackageFunctionRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public PackageFunctionRepositoryImpl(JpaContext context) {
        Assert.notNull(context, "JpaContext must not be null!");
    }

    @Override
    public Optional<Boolean> insert(String package_code,String function_code) {
        try{
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("osp_npo_package_function_insert");
            storedProcedureQuery.setParameter("package_code",package_code);
            storedProcedureQuery.setParameter("function_code",function_code);
            storedProcedureQuery.execute();
            return Optional.of(Boolean.valueOf(true));
        }
        catch(Exception e){
            e.printStackTrace();
            LOG.error(" Have error insert.PackageFunctionRepositoryImpl method :" + e.getMessage());
            return Optional.of(Boolean.valueOf(false));
        }
        }
//        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
//        try {
//            entityManagerCurrent.getTransaction().begin();
//            entityManagerCurrent.persist(packageFunctionBO);
//            entityManagerCurrent.flush();
//            entityManagerCurrent.getTransaction().commit();
//            return Optional.of(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//            LOG.error(" Have error in add.PackageFunctionRepositoryImpl method :" + e.getMessage());
//            return Optional.of(false);
//        } finally {
//            entityManagerCurrent.close();
//        }
//    }

//    @Override
//    public Optional<Boolean> update(PackageFunctionBO packageFunctionBO) {
//        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
//        try {
//            entityManagerCurrent.getTransaction().begin();
//            entityManagerCurrent.merge(packageFunctionBO);
//            entityManagerCurrent.flush();
//            entityManagerCurrent.getTransaction().commit();
//            return Optional.of(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//            LOG.error(" Have error in update.PackageFunctionRepositoryImpl method :" + e.getMessage());
//            return Optional.of(false);
//        } finally {
//            if (entityManagerCurrent != null)
//                entityManagerCurrent.close();
//        }
//    }

        @Override
    public Optional<Boolean> deleteById(Long id) {
            try {
                StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("osp_npo_package_function_delete_by_id");
                storedProcedure
                        .setParameter("id", id);
                storedProcedure.execute();
                return Optional.of(Boolean.valueOf(true));
            } catch (Exception e) {
                e.printStackTrace();
                return Optional.of(Boolean.valueOf(false));
            }
    }
    @Override
    public String packageCodeMax(){
        String result = null;
          EntityManager entityManager = entityManagerFactory.createEntityManager();
          entityManager.getTransaction().begin();
          try{
              String hql = "select Max(package_code) from PackageFunctionBO";
              result = String.valueOf(entityManager.createQuery(hql).getSingleResult());
              return result;
          }catch (Exception e) {
              e.printStackTrace();
              LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm getFunctionByFilter");
              return result;
          }
    }
    @Override
    public Optional<List<PackageFunctionBO>> selectPackageFunctionbyFilter(String stringFilter){
        List<PackageFunctionBO> result = new ArrayList<>();
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        entityManagerCurrent.getTransaction().begin();
        try{
            String hql = "select npo from PackageFunctionBO npo" + stringFilter;
            result = entityManagerCurrent.createQuery(hql).getResultList();
            entityManagerCurrent.close();
        }catch (Exception e){
            LOG.error("Have an error in method selectPackageFunctionbyFilter: "+e.getMessage());
            entityManagerCurrent.getTransaction().rollback();
            entityManagerCurrent.close();
        }
        return Optional.of(result);
    }

//    @Override
//    public Optional<List<CustomerBO>> selectByFilter(String stringFilter) {
//        try {
//            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("osp_npo_customer_select_by_filter");
//            storedProcedure
//                    .setParameter("stringFilter", stringFilter);
//            storedProcedure.execute();
//            return Optional.of((List<CustomerBO>) storedProcedure.getResultList());
//        } catch (Exception e) {
//            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm selectByFilter");
//            return Optional.of(new ArrayList<CustomerBO>());
//        }
//    }
//
//    @Override
//    public Optional<BigInteger> countTotalCustomer(String stringFilter) {
//        try {
//            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("osp_npo_customer_count_total");
//            storedProcedure.setParameter("stringFilter", stringFilter);
//            storedProcedure.execute();
//            BigInteger result = (BigInteger) storedProcedure.getSingleResult();
//            return Optional.of(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Optional.of(BigInteger.valueOf(0));
//        }
//    }


}