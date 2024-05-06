package com.vn.osp.notarialservices.contractkind.service;

import com.vn.osp.notarialservices.contractkind.domain.ContractKindBo;
import com.vn.osp.notarialservices.contractkind.dto.ContractKind;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Created by minh on 5/30/2017.
 */
public interface ContractKindService  {
    Optional<Boolean> ContractKindAdd(String name, Long entryUserId, String entryUserName, String code);
    Optional<List<ContractKind>> findContractKindByFilter(String stringFilter);
    Optional<BigInteger> countContractKindByFilter(String stringFilter);
    Optional<Boolean> UpdateContractKind(Long id, String name, Long updateUserId,String updateUserName);
    Optional<Boolean> deleteContractKind(Long id);

}
