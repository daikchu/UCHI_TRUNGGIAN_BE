package com.vn.osp.notarialservices.bankPayment.repository;

import com.vn.osp.notarialservices.bank.domain.BankBo;
import com.vn.osp.notarialservices.bankPayment.domain.BankPaymentBO;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 30/5/2017.
 */
public interface BankPaymentRepositoryCustom {
    Optional<List<BankPaymentBO>> getListBankPayment();
}

