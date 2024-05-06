package com.vn.osp.notarialservices.paymentTransaction.repository;

import com.vn.osp.notarialservices.paymentTransaction.domain.OrderMapVerification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.Optional;

@Repository
@Transactional
public class OrderMapVerifyRepositoryImpl implements OrderMapVerifyRepository{
    private static final Logger LOG = LoggerFactory.getLogger(OrderMapVerifyRepositoryImpl.class);

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<OrderMapVerification> saveOrUpdate(OrderMapVerification orderMapVerification){
        EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
        try {
            entityManagerCurrent.getTransaction().begin();
            if(orderMapVerification.getId() == null){
                entityManagerCurrent.persist(orderMapVerification);
            }else {
                entityManagerCurrent.merge(orderMapVerification);
            }
            entityManagerCurrent.getTransaction().commit();
        }catch (Exception e){
            LOG.error("Have an error in method saveOrUpdate: "+e.getMessage());
            throw e;
        }finally {
            entityManagerCurrent.clear();
            entityManagerCurrent.close();
        }
        return Optional.of(orderMapVerification);
    }
}
