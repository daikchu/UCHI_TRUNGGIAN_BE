package com.vn.osp.notarialservices.function.repository;

import com.vn.osp.notarialservices.function.domain.FunctionBO;
import com.vn.osp.notarialservices.function.dto.FunctionDTO;
import com.vn.osp.notarialservices.packagefunction.domain.GroupRoleFunctionTemplateBO;
import com.vn.osp.notarialservices.packagefunction.dto.GroupRoleFunctionTemplateDTO;
import com.vn.osp.notarialservices.province.domain.ProvinceBo;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Created by ProBook on 5/25/2017.
 */
public interface FunctionRepositoryCustom {
    void findByOverridingMethod();

    /***
     *
     * @paramid
     */
    Optional<List<FunctionBO>> getByFunction(String stringFilter);
    Optional<List<FunctionBO>> getFunctionByFilter(String stringFilter);
    Optional<List<GroupRoleFunctionTemplateBO>> getGroupRoleFunctionTemplateDTOByFilter(String stringFilter);
}
