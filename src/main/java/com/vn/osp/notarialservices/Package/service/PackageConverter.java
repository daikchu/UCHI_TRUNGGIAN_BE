package com.vn.osp.notarialservices.Package.service;

import com.vn.osp.notarialservices.Package.domain.PackageBO;
import com.vn.osp.notarialservices.Package.dto.PackageDTO;
import com.vn.osp.notarialservices.common.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PackageConverter implements Converter<PackageBO, PackageDTO> {
    @Override
    public PackageDTO convert(final PackageBO source) {
        return new PackageDTO(
                source.getId(),
                source.getCode(),
                source.getName(),
                source.getUser_num(),
                source.getDescription(),
                source.getNote());
    }

    public List<PackageDTO> converts(final List<PackageBO> source) {
        return source.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public PackageBO convert(final PackageDTO source) {
        PackageBO packageBO = new PackageBO();
        packageBO.setId(source.getSid());
        packageBO.setCode(source.getCode());
        packageBO.setName(source.getName());
        packageBO.setUser_num(source.getUser_num());
        packageBO.setDescription(source.getDescription());
        packageBO.setNote(source.getNote());
        return packageBO;
    }
}
