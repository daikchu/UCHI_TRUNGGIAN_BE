package com.vn.osp.notarialservices.bank.service;

import com.vn.osp.notarialservices.bank.dto.Bank;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 30/5/2017.
 */
public interface BankService {
    Optional<Boolean> addBank(String name, Long entryUserId, String entryUserName, String code, Long active);
    Optional<Boolean> updateBank(Long id ,String name , Long updateUserId, String updateUserName ,Long active);
    Optional<List<Bank>> findBankByFilter (String stringFilter);
    Optional<BigInteger> countBankByFilter(String stringFilter);
    Optional<Boolean> deleteBank(Long id );
}
