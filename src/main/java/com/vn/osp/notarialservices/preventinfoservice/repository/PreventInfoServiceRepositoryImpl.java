package com.vn.osp.notarialservices.preventinfoservice.repository;

import com.vn.osp.notarialservices.preventinfoservice.domain.PreventInfoServiceBo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 12/6/2017.
 */
public class PreventInfoServiceRepositoryImpl implements PreventInfoServiceRepositoryCustom{
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(PreventInfoServiceRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired

    public PreventInfoServiceRepositoryImpl(JpaContext context) {
        Assert.notNull(context, "JpaContext must not be null!");
    }

    public void findByOverridingMethod() {
        LOG.info("A method overriding a finder was invoked!");
    }
    @Override
    public Optional<List<PreventInfoServiceBo>> findPreventInfoServiceByFilter(String stringFilter){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("findPreventInfoServiceByFilter");
            storedProcedure
                    .setParameter("stringFilter", stringFilter);


            storedProcedure.execute();
            List<PreventInfoServiceBo> preventInfoServiceBos = (List<PreventInfoServiceBo>) storedProcedure.getResultList();
            return Optional.of(preventInfoServiceBos);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(new ArrayList<PreventInfoServiceBo>());
        }
    }
}
