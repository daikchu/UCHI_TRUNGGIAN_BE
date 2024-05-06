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
package com.vn.osp.notarialservices.Package.repository;
import com.vn.osp.notarialservices.Package.domain.PackageBO;
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

public class PackageRepositoryImpl implements PackageRepositoryCustom {

    private static final Logger LOG = LoggerFactory.getLogger(PackageRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public PackageRepositoryImpl(JpaContext context) {
        Assert.notNull(context, "JpaContext must not be null!");
    }

    @Override
    public Optional<Boolean> insert(PackageBO packageBO) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        try {
            entityManagerCurrent.getTransaction().begin();
            entityManagerCurrent.persist(packageBO);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(" Have error in insert.PackageRepositoryImpl method :" + e.getMessage());
            return Optional.of(false);
        } finally {
            entityManagerCurrent.close();
        }
    }

    @Override
    public Optional<Boolean> update(PackageBO packageBO) {
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        try {
            entityManagerCurrent.getTransaction().begin();
            entityManagerCurrent.merge(packageBO);
            entityManagerCurrent.flush();
            entityManagerCurrent.getTransaction().commit();
            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(" Have error in update.PackageRepositoryImpl method :" + e.getMessage());
            return Optional.of(false);
        } finally {
            if (entityManagerCurrent != null)
                entityManagerCurrent.close();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("osp_npo_package_delete_by_id");
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
    public Optional<BigInteger> packageCountTotalByFilter(String stringFilter){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("osp_npo_package_count_total");
            storedProcedure.setParameter("stringFilter", stringFilter);
            storedProcedure.execute();
            BigInteger result = (BigInteger) storedProcedure.getSingleResult();
            return Optional.of(result);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(BigInteger.valueOf(0));
        }
    }

    @Override
    public Optional<List<PackageBO>> selectPackagebyFilter(String stringFilter) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("osp_npo_package_select_by_filter");
            storedProcedure
                    .setParameter("stringFilter", stringFilter);
            storedProcedure.execute();
            List<PackageBO> result= storedProcedure.getResultList();
            return Optional.of(result);
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm selectByFilter");
            return Optional.of(new ArrayList<PackageBO>());
        }
    }

//    @Override
//    public Optional<List<PackageBO>> selectPackagebyFilter(String stringFilter){
//        List<PackageBO> result = new ArrayList<>();
//        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
//        entityManagerCurrent.getTransaction().begin();
//        try{
//            String hql = "select npo from PackageBO npo" + stringFilter;
//            result = entityManagerCurrent.createQuery(hql).getResultList();
//            entityManagerCurrent.close();
//        }catch (Exception e){
//            LOG.error("Have an error in method selectPackagebyFilter: "+e.getMessage());
//            entityManagerCurrent.getTransaction().rollback();
//            entityManagerCurrent.close();
//        }
//        return Optional.of(result);
//    }

}
