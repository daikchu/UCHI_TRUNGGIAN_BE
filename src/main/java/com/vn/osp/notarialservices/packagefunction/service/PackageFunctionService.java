package com.vn.osp.notarialservices.packagefunction.service;

import com.vn.osp.notarialservices.packagefunction.dto.PackageFunctionsDTO;

import java.util.List;
import java.util.Optional;

public interface PackageFunctionService {
    Optional<Boolean> insert(String package_code,String function_code);
//    Optional<Boolean> update(PackageFunctionsDTO packageFunctionsDTO);
    Optional<Boolean> deleteById(Long id);
     String packageCodeMax();

    Optional<List<PackageFunctionsDTO>> selectPackageFunctionbyFilter(String stringFilter);
//    Optional<BigInteger> countTotalCustomer(String stringFilter);
//    CustomerInfoDTO getCustomerInfoByCode(String code);
}
