package com.vn.osp.notarialservices.Package.service;

import com.vn.osp.notarialservices.Package.dto.PackageDTO;
import com.vn.osp.notarialservices.Package.dto.PackageFuncDTO;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface PackageService {
    Optional<Boolean> insert(PackageFuncDTO packageFuncDTO);
    Optional<Boolean> update(PackageFuncDTO packageFuncDTO);
    Optional<Boolean> deleteById(Long id);
    Optional<BigInteger> packageCountTotalByFilter(String stringFilter);
    Optional<List<PackageDTO>> selectPackagebyFilter(String stringFilter);
}
