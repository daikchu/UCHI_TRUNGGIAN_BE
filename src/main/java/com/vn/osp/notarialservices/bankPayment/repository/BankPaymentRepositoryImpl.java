package com.vn.osp.notarialservices.bankPayment.repository;

import com.vn.osp.notarialservices.bank.domain.BankBo;
import com.vn.osp.notarialservices.bankPayment.domain.BankPaymentBO;
import com.vn.osp.notarialservices.identityAuthentication.domain.BankBeneficiaryBO;
import org.slf4j.Logger;
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
 * Created by Admin on 30/5/2017.
 */

@Repository
@Transactional
public class BankPaymentRepositoryImpl implements BankPaymentRepositoryCustom {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(BankPaymentRepositoryImpl.class);

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public BankPaymentRepositoryImpl(JpaContext context) {
        Assert.notNull(context, "JpaContext must not be null!");
    }

    @Override
    public Optional<List<BankPaymentBO>> getListBankPayment(){
        List<BankPaymentBO> result = new ArrayList<>();
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        entityManagerCurrent.getTransaction().begin();
        try{
            String hql = "select bank_payment from BankPaymentBO bank_payment";
            result = entityManagerCurrent.createQuery(hql).getResultList();
            entityManagerCurrent.close();
        }catch (Exception e){
            LOG.error("Have an error in method getListBankPayment: "+e.getMessage());
            entityManagerCurrent.getTransaction().rollback();
            entityManagerCurrent.close();
        }
        return Optional.of(result);
    }


}
