package com.vn.osp.notarialservices.contracttemplate.service;

import com.vn.osp.notarialservices.contracttemplate.domain.ContractTempBo;
import com.vn.osp.notarialservices.contracttemplate.dto.AddContractTemp;
import com.vn.osp.notarialservices.contracttemplate.dto.ContractTemp;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 1/6/2017.
 */
public interface ContractTempService {
    Optional<Boolean> addContractTemp(AddContractTemp contractTemp);
    Optional<Boolean> updateContractTemp(ContractTemp contractTemp);
    Optional<List<ContractTemp>> findContractTempByFilter(String stringFilter) ;
    Optional<BigInteger> countContractTempByFilter(String stringFilter);
    Optional<Boolean> deleteContractTemp(Long id );
    Optional<Boolean> getByCode(String code);
}
