package com.vn.osp.notarialservices.contracttemplate.repository;

import com.vn.osp.notarialservices.contracttemplate.domain.ContractTempBo;
import com.vn.osp.notarialservices.contracttemplate.dto.AddContractTemp;
import com.vn.osp.notarialservices.contracttemplate.dto.ContractTemp;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 1/6/2017.
 */
public interface ContractTempRepositoryCustom {
    /**
     * Method actually triggering a finder but being overridden.
     */
    void findByOverridingMethod();

    /***
     *
     * @param id
     */
    void delete(Integer id);
    Optional<Boolean> addContractTemp(AddContractTemp contractTemp);
    Optional<List<ContractTempBo>> findContractTempByFilter(String stringFilter);
    Optional<BigInteger> countContractTempByFilter(String stringFilter);
    Optional<Boolean> updateContractTemp(ContractTemp item);
    Optional<Boolean>deleteContractTemp(Long id);
    Optional<Boolean> getByCode(String code);
}
