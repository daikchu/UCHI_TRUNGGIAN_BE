package com.vn.osp.notarialservices.packagefunction.service;


import com.vn.osp.notarialservices.common.converter.Converter;
import com.vn.osp.notarialservices.packagefunction.domain.GroupRoleFunctionTemplateBO;
import com.vn.osp.notarialservices.packagefunction.domain.PackageFunctionBO;
import com.vn.osp.notarialservices.packagefunction.dto.GroupRoleFunctionTemplateDTO;
import com.vn.osp.notarialservices.packagefunction.dto.PackageFunctionsDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupRoleFunctionTemplateConverter implements Converter<GroupRoleFunctionTemplateBO, GroupRoleFunctionTemplateDTO> {

    @Override
    public GroupRoleFunctionTemplateDTO convert(final GroupRoleFunctionTemplateBO source) {
        return new GroupRoleFunctionTemplateDTO(
                source.getId(),
                source.getGroup_role_code(),
                source.getGroup_role_name(),
                source.getFunction_code());
    }

    public List<GroupRoleFunctionTemplateDTO> converts(final List<GroupRoleFunctionTemplateBO> source) {
            return source.stream()
                    .map(this::convert)
                    .collect(Collectors.toList());
    }

    @Override
    public GroupRoleFunctionTemplateBO convert(final GroupRoleFunctionTemplateDTO source) {
        GroupRoleFunctionTemplateBO groupRoleFunctionTemplateBO = new GroupRoleFunctionTemplateBO();
        groupRoleFunctionTemplateBO.setId(source.getSid());
        groupRoleFunctionTemplateBO.setGroup_role_code(source.getGroup_role_code());
        groupRoleFunctionTemplateBO.setGroup_role_name(source.getGroup_role_name());
        groupRoleFunctionTemplateBO.setFunction_code(source.getFunction_code());
        return groupRoleFunctionTemplateBO;
    }

}

