package com.vn.osp.notarialservices.bank.repository;

import com.vn.osp.notarialservices.bank.domain.BankBo;
import org.bouncycastle.crypto.agreement.srp.SRP6Client;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 30/5/2017.
 */
public class BankRepositoryImpl implements BankRepositoryCustom {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(BankRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired

    public BankRepositoryImpl(JpaContext context) {
        Assert.notNull(context, "JpaContext must not be null!");
    }

    public void findByOverridingMethod() {
        LOG.info("A method overriding a finder was invoked!");
    }

    @Override
    public void delete(Integer id) {
        LOG.info("A method overriding a finder was invoked!");
    }

    @Override
    public Optional<Boolean> addBank(String name, Long entryUserId, String entryUserName, String code, Long active){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("addBank");
            storedProcedure
                    .setParameter("name", name)
                    .setParameter("entry_user_id", entryUserId)
                    .setParameter("entry_user_name", entryUserName)
                    .setParameter("code", code)
                    .setParameter("active", active);

            storedProcedure.execute();
            return Optional.of(Boolean.valueOf(true));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(Boolean.valueOf(false));
        }
    }
    @Override
    public Optional<Boolean> updateBank(Long id,String name , Long updateUserId, String updateUserName ,Long active){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("updateBank");
            storedProcedure
                    .setParameter("id", id)
                    .setParameter("name", name)
                    .setParameter("update_user_id", updateUserId)
                    .setParameter("update_user_name", updateUserName)
                    /*.setParameter("code", code)*/
                    .setParameter("active",active);

            storedProcedure.execute();
            return Optional.of(Boolean.valueOf(true));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(Boolean.valueOf(false));
        }
    }
    @Override
    public Optional<Boolean> deleteBank(Long id){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("deleteBank");
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
    public Optional<List<BankBo>> findBankByFilter(String stringFilter){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("findBankByFilter");
            storedProcedure
                    .setParameter("stringFilter", stringFilter);


            storedProcedure.execute();
            List<BankBo> bankBo = (List<BankBo>) storedProcedure.getResultList();
            return Optional.of(bankBo);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(new ArrayList<BankBo>());
        }
    }
    @Override
    public Optional<BigInteger> countBankByFilter(String stringFilter){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("countBankByFilter");
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
