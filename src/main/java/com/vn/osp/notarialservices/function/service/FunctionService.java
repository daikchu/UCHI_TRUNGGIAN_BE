package com.vn.osp.notarialservices.function.service;


import com.vn.osp.notarialservices.function.dto.FunctionDTO;
import com.vn.osp.notarialservices.packagefunction.dto.GroupRoleFunctionTemplateDTO;

import java.util.List;
import java.util.Optional;

public interface FunctionService {
    Optional<List<FunctionDTO>> getByFunction(String stringFilter);
    Optional<List<FunctionDTO>> getFunctionByFilter(String stringFilter);
    Optional<List<GroupRoleFunctionTemplateDTO>> getGroupRoleFunctionTemplateDTOByFilter(String stringFilter);
 }
