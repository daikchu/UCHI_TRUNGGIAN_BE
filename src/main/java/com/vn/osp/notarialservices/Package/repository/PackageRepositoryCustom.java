/*
 * Copyright 2008-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vn.osp.notarialservices.Package.repository;

import com.vn.osp.notarialservices.Package.domain.PackageBO;
import com.vn.osp.notarialservices.Package.dto.PackageDTO;
import com.vn.osp.notarialservices.packagefunction.domain.PackageFunctionBO;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Simple interface for custom methods on the repository for {@code ContractHistoryInfor}s.
 *
 * @author manhtran on 27/10/2016.
 */
public interface PackageRepositoryCustom {
    Optional<Boolean> insert(PackageBO packageBO);
    Optional<Boolean> update(PackageBO packageBO);
    Optional<Boolean> deleteById(Long id);
    Optional<BigInteger> packageCountTotalByFilter(String stringFilter);
    Optional<List<PackageBO>> selectPackagebyFilter(String stringFilter);
//    Optional<Boolean> insert(String package_code, String function_code);
//    //    Optional<Boolean> update(PackageFunctionBO packageFunctionBO);
//    Optional<Boolean> deleteById(Long id);
//    String packageCodeMax();
//    Optional<List<PackageFunctionBO>> selectPackageFunctionbyFilter(String stringFilter);
//    Optional<List<CustomerBO>> selectByFilter(String stringFilter);
//    Optional<BigInteger> countTotalCustomer(String stringFilter);
}
