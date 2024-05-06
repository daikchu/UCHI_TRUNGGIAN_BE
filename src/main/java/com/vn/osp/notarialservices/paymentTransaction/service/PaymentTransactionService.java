package com.vn.osp.notarialservices.paymentTransaction.service;

import com.vn.osp.notarialservices.paymentTransaction.domain.PaymentTransactions;
import com.vn.osp.notarialservices.paymentTransaction.dto.PaymentTransactionDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentTransactionService {
    Optional<PaymentTransactionDTO> insert(PaymentTransactionDTO paymentTransactions, boolean isBySystem);
    Optional<PaymentTransactionDTO> update(PaymentTransactionDTO paymentTransactions);
    Optional<Boolean> deleteById(Long transaction_id);
    Optional<PaymentTransactionDTO> get(String transaction_id);
    Optional<PaymentTransactionDTO> getDetail(String transaction_id);
    Optional<PaymentTransactionDTO> getById(Long id);
    Optional<List<PaymentTransactionDTO>> filter(String order_id);
    Optional<List<PaymentTransactionDTO>> page(String order_id);
    Optional<Long> count(String order_id);
    Optional<PaymentTransactions> saveOrUpdateTransactionBO(PaymentTransactions paymentTransactions);
    Boolean checkExitsRefTransactionId(String refTransactionId);
    Optional<PaymentTransactions> getPaymentTransaction(String stringFilter);
    Optional<List<PaymentTransactions>> updateStatusOrderIdAfter15Minutes();
}
