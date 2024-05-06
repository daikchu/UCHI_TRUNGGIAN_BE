package com.vn.osp.notarialservices.province.repository;

import com.vn.osp.notarialservices.province.domain.ProvinceBo;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.validation.ConstraintViolationException;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.Long;

/**
 * Created by ProBook on 5/25/2017.
 */
public class ProvinceRepositoryImpl implements ProvinceRepositoryCustom {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(ProvinceRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired

    public ProvinceRepositoryImpl(JpaContext context) {
        Assert.notNull(context, "JpaContext must not be null!");
    }

    public void findByOverridingMethod() {
        LOG.info("A method overriding a finder was invoked!");
    }

    @Override
    public Optional<Boolean> AddProvince(String name , Long entryUserId, String entryUserName , String code){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("addProvince");
            storedProcedure
                    .setParameter("name", name)
                    .setParameter("entry_user_id", entryUserId)
                    .setParameter("entry_user_name", entryUserName)
                    .setParameter("code", code);

            storedProcedure.execute();

            return Optional.of(Boolean.valueOf(true));

        }
        /*catch (MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            return Optional.of(Boolean.valueOf(false));
        }*/
        catch(Exception e){
            e.printStackTrace();
            return Optional.of(Boolean.valueOf(false));
        }




    }
    @Override
    public Optional<Boolean> UpdateProvince(Long id,String name , Long updateUserId, String updateUserName ){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("updateProvince");
            storedProcedure
                    .setParameter("id", id)
                    .setParameter("name", name)
                    .setParameter("update_user_id", updateUserId)
                    .setParameter("update_user_name", updateUserName);


            storedProcedure.execute();
            return Optional.of(Boolean.valueOf(true));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(Boolean.valueOf(false));
        }
    }
    @Override
    public Optional<Boolean> deleteProvince(Long id){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("deleteProvince");
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
    public Optional<List<ProvinceBo>> findProvinceByFilter(String stringFilter){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("findProvinceByFilter");
            storedProcedure
                    .setParameter("stringFilter", stringFilter);


            storedProcedure.execute();
            List<ProvinceBo> provinceBo = (List<ProvinceBo>) storedProcedure.getResultList();
            return Optional.of(provinceBo);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(new ArrayList<ProvinceBo>());
        }
    }
    @Override
    public Optional<BigInteger> countProvinceByFilter(String stringFilter){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("countProvinceByFilter");
            storedProcedure
                    .setParameter("stringFilter", stringFilter);


            storedProcedure.execute();
            BigInteger result =(BigInteger)storedProcedure.getSingleResult();
            return Optional.of(result);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(BigInteger.valueOf(0));
        }
    }
}
