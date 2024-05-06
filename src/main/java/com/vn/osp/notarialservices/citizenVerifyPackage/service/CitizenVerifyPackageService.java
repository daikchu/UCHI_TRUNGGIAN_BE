package com.vn.osp.notarialservices.citizenVerifyPackage.service;

import com.vn.osp.notarialservices.citizenVerifyPackage.domain.CitizenVerifyPackageBO;

import java.util.List;
import java.util.Optional;

public interface CitizenVerifyPackageService {
    Optional<List<CitizenVerifyPackageBO>> getAll();
    Optional<CitizenVerifyPackageBO> getByCode(String packageCode);
}
