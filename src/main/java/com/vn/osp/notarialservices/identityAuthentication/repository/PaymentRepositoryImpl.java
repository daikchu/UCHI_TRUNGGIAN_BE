package com.vn.osp.notarialservices.identityAuthentication.repository;

import com.vn.osp.notarialservices.identityAuthentication.domain.BankBeneficiaryBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentRepositoryImpl implements PaymentRepositoryCustom {
    private static final Logger LOG = LoggerFactory.getLogger(PaymentRepositoryImpl.class);

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    public PaymentRepositoryImpl(JpaContext context) {
        Assert.notNull(context, "JpaContext must not be null!");
    }
    @Override
    public Optional<List<BankBeneficiaryBO>> getListBankBeneficiary(Integer genQRTransType){
        List<BankBeneficiaryBO> result = new ArrayList<>();
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        entityManagerCurrent.getTransaction().begin();
        try{
            String hql = "select bank_beneficiary from BankBeneficiaryBO bank_beneficiary where bank_beneficiary.genQRTransType = '"+genQRTransType+"'";
            result = entityManagerCurrent.createQuery(hql).getResultList();
            entityManagerCurrent.close();
        }catch (Exception e){
            LOG.error("Have an error in method getListBankBeneficiary: "+e.getMessage());
            entityManagerCurrent.getTransaction().rollback();
            entityManagerCurrent.close();
            return Optional.of(new ArrayList<>());
        }
        return Optional.of(result);
    }
}
