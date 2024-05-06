package com.vn.osp.notarialservices.contractkind.repository;


import com.vn.osp.notarialservices.contractkind.domain.ContractKindBo;
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
 * Created by minh on 5/30/2017.
 */
public class ContractKindRepositoryImpl implements ContractKindRepositoryCustom {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(ContractKindRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired

    public ContractKindRepositoryImpl(JpaContext context) {
        Assert.notNull(context, "JpaContext must not be null!");
    }

    public void findByOverridingMethod() {
        LOG.info("A method overriding a finder was invoked!");
    }

    @Override
    public Optional<Boolean> ContractKindAdd(String name , Long entryUserId, String entryUserName , String code){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("osp_contract_kind_add");
            storedProcedure
                    .setParameter("name", name)
                    .setParameter("entry_user_id", entryUserId)
                    .setParameter("entry_user_name", entryUserName)
                    .setParameter("contract_kind_code", code);

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
    public Optional<Boolean> UpdateContractKind(Long id,String name , Long updateUserId, String updateUserName ){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("osp_contract_kind_update");
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
    public Optional<Boolean> deleteContractKind(Long id){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("osp_contract_kind_delete");
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
    public Optional<List<ContractKindBo>> findContractKindByFilter(String stringFilter){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("osp_contract_kind_select_filter");
            storedProcedure
                    .setParameter("stringFilter", stringFilter);


            storedProcedure.execute();
            List<ContractKindBo> contractKindBos = (List<ContractKindBo>) storedProcedure.getResultList();
            return Optional.of(contractKindBos);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(new ArrayList<ContractKindBo>());
        }
    }
    @Override
    public Optional<BigInteger> countContractKindByFilter(String stringFilter){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("osp_contract_kind_count_filter");
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
