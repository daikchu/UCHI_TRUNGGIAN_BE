package com.vn.osp.notarialservices.function.repository;

import com.vn.osp.notarialservices.function.domain.FunctionBO;
import com.vn.osp.notarialservices.function.dto.FunctionDTO;
import com.vn.osp.notarialservices.packagefunction.domain.GroupRoleFunctionTemplateBO;
import com.vn.osp.notarialservices.province.domain.ProvinceBo;
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
 * Created by ProBook on 5/25/2017.
 */
public class FunctionRepositoryImpl implements FunctionRepositoryCustom {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(FunctionRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    @Autowired

    public FunctionRepositoryImpl(JpaContext context) {
        Assert.notNull(context, "JpaContext must not be null!");
    }

    public void findByOverridingMethod() {
        LOG.info("A method overriding a finder was invoked!");
    }

    @Override
    public Optional<List<FunctionBO>> getByFunction(String stringFilter) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("osp_npo_function_by_code");
            storedProcedureQuery.setParameter("stringFilter", stringFilter);
            storedProcedureQuery.execute();
            List<FunctionBO> result = (List<FunctionBO>) storedProcedureQuery.getResultList();
            return Optional.of(result);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm getByFunction");
            return Optional.of(new ArrayList<FunctionBO>());
        }
    }

    @Override
    public Optional<List<FunctionBO>> getFunctionByFilter(String stringFilter) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("osp_npo_function_by_type");
            storedProcedureQuery.setParameter("stringFilter", stringFilter);
            storedProcedureQuery.execute();
            ArrayList<FunctionBO> result = (ArrayList<FunctionBO>) storedProcedureQuery.getResultList();
            return Optional.of(result);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm getFunctionByFilter");
            return Optional.of(new ArrayList<FunctionBO>());
        }
    }
    @Override
    public Optional<List<GroupRoleFunctionTemplateBO>> getGroupRoleFunctionTemplateDTOByFilter(String stringFilter){
            List<GroupRoleFunctionTemplateBO> result = new ArrayList<>();
            EntityManager entityManagerCurrent = entityManagerFactory.createEntityManager();
            entityManagerCurrent.getTransaction().begin();
//            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("osp_npo_group_role_function_template");
//            storedProcedureQuery.execute();
//            List<GroupRoleFunctionTemplateBO> result = (List<GroupRoleFunctionTemplateBO>) storedProcedureQuery.getResultList();
//            return Optional.of(result);8
        try {
            String hql = "select DISTINCT group_role_code,group_role_name from GroupRoleFunctionTemplateBO " +stringFilter;
            result = entityManagerCurrent.createQuery(hql).getResultList();
//            entityManagerCurrent.close();
            return Optional.of(result);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm getFunctionByFilter");
            return Optional.of(new ArrayList<GroupRoleFunctionTemplateBO>());
        }
    }
}
