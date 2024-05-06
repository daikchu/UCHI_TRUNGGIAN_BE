package com.vn.osp.notarialservices.bank.repository;

import com.vn.osp.notarialservices.bank.domain.BankBo;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 30/5/2017.
 */
public interface BankRepositoryCustom {
    void findByOverridingMethod();

    /***
     *
     * @param id
     */
    void delete(Integer id);



    Optional<Boolean> addBank(String name , Long entryUserId, String entryUserName, String code, Long active);
    Optional<List<BankBo>> findBankByFilter (String stringFilter);
    Optional<BigInteger> countBankByFilter(String stringFilter);
    Optional<Boolean> updateBank(Long id, String name, Long updateUserId,String updateUserName,Long active);
    Optional<Boolean>deleteBank(Long id);



}

