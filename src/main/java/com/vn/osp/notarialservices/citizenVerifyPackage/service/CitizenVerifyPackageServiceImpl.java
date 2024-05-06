package com.vn.osp.notarialservices.citizenVerifyPackage.service;

import com.vn.osp.notarialservices.citizenVerifyPackage.domain.CitizenVerifyPackageBO;
import com.vn.osp.notarialservices.citizenVerifyPackage.repository.CitizenVerifyPackageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitizenVerifyPackageServiceImpl implements CitizenVerifyPackageService{
    private final CitizenVerifyPackageRepository citizenVerifyPackageRepository;

    @Autowired
    public CitizenVerifyPackageServiceImpl(CitizenVerifyPackageRepository citizenVerifyPackageRepository) {
        this.citizenVerifyPackageRepository = citizenVerifyPackageRepository;
    }

    @Override
    public Optional<List<CitizenVerifyPackageBO>> getAll() {
        return Optional.of(citizenVerifyPackageRepository.findAll());
    }

    @Override
    public Optional<CitizenVerifyPackageBO> getByCode(String packageCode) {
        if(StringUtils.isBlank(packageCode)) return Optional.empty();
        return Optional.of(citizenVerifyPackageRepository.findFirstByCodeEquals(packageCode));
    }
}
