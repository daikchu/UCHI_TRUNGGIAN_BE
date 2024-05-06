package com.vn.osp.notarialservices.contracttemplate.repository;

import com.vn.osp.notarialservices.contracttemplate.domain.ContractTempBo;
import com.vn.osp.notarialservices.contracttemplate.dto.AddContractTemp;
import com.vn.osp.notarialservices.contracttemplate.dto.ContractTemp;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 1/6/2017.
 */
public class ContractTempRepositoryImpl implements ContractTempRepositoryCustom {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(ContractTempRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired

    public ContractTempRepositoryImpl(JpaContext context) {
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
    public Optional<Boolean> addContractTemp(AddContractTemp item){
        ContractTempBo itemAdd=new ContractTempBo();
        try {
            long maxValue=(long)entityManager.createQuery("select max(temp.code_template) from ContractTempBo temp").getSingleResult();

            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("addContractTemp");
            storedProcedure
                    .setParameter("name", item.getName())
                    .setParameter("code_temp", item.getCode())
                    .setParameter("relate_object_A_display", item.getRelate_object_A_display())
                    .setParameter("relate_object_B_display", item.getRelate_object_B_display())
                    .setParameter("relate_object_C_display", item.getRelate_object_C_display())
                    .setParameter("description", item.getDescription())
                    .setParameter("active_flg", item.getActive_flg())
                    .setParameter("period_flag", item.getPeriod_flag())
                    .setParameter("mortage_cancel_func", item.getMortage_cancel_func())
                    .setParameter("entry_user_id", item.getEntry_user_id())
                    .setParameter("entry_user_name", item.getEntry_user_name())
                    .setParameter("kind_html", item.getKind_html())
                    .setParameter("code_template",(maxValue+1))
                    .setParameter("kind_id",item.getKind_id());


            storedProcedure.execute();
            return Optional.of(Boolean.valueOf(true));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(Boolean.valueOf(false));
        }
    }
    @Override
    public Optional<Boolean> updateContractTemp(ContractTemp item){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("updateContractTemp");
            storedProcedure
                    .setParameter("id", item.getSid())
                    .setParameter("name", item.getName())
                    .setParameter("code_temp", item.getCode())
                    .setParameter("relate_object_A_display", item.getRelate_object_A_display())
                    .setParameter("relate_object_B_display", item.getRelate_object_B_display())
                    .setParameter("relate_object_C_display", item.getRelate_object_C_display())
                    .setParameter("description", item.getDescription())
                    .setParameter("active_flg", item.getActive_flg())
                    .setParameter("period_flag", item.getPeriod_flag())
                    .setParameter("mortage_cancel_func", item.getMortage_cancel_func())
                    .setParameter("update_user_id", item.getUpdate_user_id())
                    .setParameter("update_user_name", item.getUpdate_user_name())
                    .setParameter("kind_html", item.getKind_html())
                    .setParameter("kind_id",item.getKind_id());


            storedProcedure.execute();
            return Optional.of(Boolean.valueOf(true));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(Boolean.valueOf(false));
        }
    }


    @Override
    public Optional<Boolean> deleteContractTemp(Long id){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("deleteContractTemp");
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
    public Optional<List<ContractTempBo>> findContractTempByFilter(String stringFilter) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("findContractTempByFilter");
            storedProcedure
                    .setParameter("stringFilter", stringFilter);
            storedProcedure.execute();
            List<ContractTempBo> contractTempBo = (List<ContractTempBo>) storedProcedure.getResultList();
            return Optional.of(contractTempBo);
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm findContractTempByFilter");
            return Optional.of(new ArrayList<ContractTempBo>());
        }
    }
    @Override
    public Optional<BigInteger> countContractTempByFilter(String stringFilter){
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("countContractTempByFilter");
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

    @Override
    public Optional<Boolean> getByCode(String code) {
        try {
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("countContractTemplateByCode");
            storedProcedure
                    .setParameter("code_temp", code);
            storedProcedure.execute();
            BigInteger result =(BigInteger)storedProcedure.getSingleResult();
            if (result.compareTo(BigInteger.valueOf(0)) > 0) {
                return Optional.of(true);
            }else{
                return Optional.of(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(false);
        }
    }
}
