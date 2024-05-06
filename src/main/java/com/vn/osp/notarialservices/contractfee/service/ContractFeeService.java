package com.vn.osp.notarialservices.contractfee.service;

import com.vn.osp.notarialservices.contractfee.domain.ContractFeeBO;
import com.vn.osp.notarialservices.contractfee.dto.ContractFeeCodeTemplate;

import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 2018-05-09.
 */
public interface ContractFeeService {
    Optional<Integer> addContractFee(ContractFeeBO contractFeeBO);
    Optional<Integer> updateContractFee(ContractFeeBO contractFeeBO);
    Optional<List<ContractFeeBO>> getContractFeeCode(int page,String codeContract);
    Optional<List<ContractFeeBO>> getContractFeeAll();
    Optional<Long> countContractFeeAll(String codeContract);

    Optional<ContractFeeBO> getContractFeeId(int id);

    Boolean deleteContractFee(int id);

    Optional<List<ContractFeeBO>> getContractFeeOnlyCode(Long codeContract);
    Optional<List<ContractFeeBO>> getContractFeeOnlyCodeExceptId(Long codeContract, int id);

    Optional<List<ContractFeeBO>> getContractFeeTcccCall(Long codeContract, long notaryCost);

    Optional<List<ContractFeeCodeTemplate>> getContractFeeCodeJoinContractTemplate(int page, String codeContract,String codeKind);
    Optional<Long> countContractFeeAllInnerJoinContractTemplate(String codeContract,String codeKind);
    // test
    Optional<List<ContractFeeCodeTemplate>> getTest();


}
