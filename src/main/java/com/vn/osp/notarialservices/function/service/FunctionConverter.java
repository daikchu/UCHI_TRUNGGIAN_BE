package com.vn.osp.notarialservices.function.service;

import com.vn.osp.notarialservices.common.converter.Converter;
import com.vn.osp.notarialservices.function.domain.FunctionBO;
import com.vn.osp.notarialservices.function.dto.FunctionDTO;
import com.vn.osp.notarialservices.packagefunction.domain.GroupRoleFunctionTemplateBO;
import com.vn.osp.notarialservices.packagefunction.dto.GroupRoleFunctionTemplateDTO;
import com.vn.osp.notarialservices.packagefunction.service.GroupRoleFunctionTemplateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FunctionConverter implements Converter<FunctionBO, FunctionDTO> {
    private final GroupRoleFunctionTemplateConverter groupRoleFunctionTemplateConverter;
    @Autowired
    public FunctionConverter(GroupRoleFunctionTemplateConverter groupRoleFunctionTemplateConverter){
        this.groupRoleFunctionTemplateConverter = groupRoleFunctionTemplateConverter;
    }
    @Override
    public FunctionDTO convert(FunctionBO source) {
        return new FunctionDTO(source.getId(),
                source.getName(),
                source.getParent_code(),
                source.getType());
    }
    @Override
    public FunctionBO convert(final FunctionDTO source) {
        FunctionBO functionBO = new FunctionBO();
        functionBO.setId(source.getCode());
        functionBO.setName(source.getName());
        functionBO.setParent_code(source.getParent_code());
        functionBO.setType(source.getType());
        return functionBO;
    }

    public List<FunctionDTO> converts(final List<FunctionBO> source) {
        return source.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public List<GroupRoleFunctionTemplateDTO> convertsToDTO(List<GroupRoleFunctionTemplateBO> source) {
        List<GroupRoleFunctionTemplateDTO> groupRoleFunctionTemplateDTOS = new ArrayList<>();
        for (GroupRoleFunctionTemplateBO groupRoleFunctionTemplateBO : source){
            groupRoleFunctionTemplateDTOS.add(groupRoleFunctionTemplateConverter.convert(groupRoleFunctionTemplateBO));
        }
        return groupRoleFunctionTemplateDTOS;
//         fore(FunctionBO functionBO:Optional.of(source)){
//
//         }
//        return source.stream()
//                .map(this::convert)
//                .collect(Collectors.toList());
    }

}
