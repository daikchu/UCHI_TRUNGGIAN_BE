package com.vn.osp.notarialservices.paymentTransaction.repository;
import com.vn.osp.notarialservices.paymentTransaction.domain.PaymentTransactions;
import com.vn.osp.notarialservices.paymentTransaction.dto.PaymentTransactionDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentTransactionRepositoryCustom {
    Optional<PaymentTransactions>  saveOrUpdateTransactionBO(PaymentTransactions paymentTransactions);
    Boolean checkExitsRefTransactionId(String refTransactionId);
    Optional<PaymentTransactions> insert(PaymentTransactions paymentTransactions);
    Optional<PaymentTransactions> update(PaymentTransactions paymentTransactions);
    Optional<Boolean> deleteById(Long transaction_id);
    Optional<PaymentTransactionDTO> getDetail(String transaction_id);
    Optional<PaymentTransactionDTO> getDetailById(Long id);
    Optional<List<PaymentTransactionDTO>> filter(String order_id);
    Optional<List<PaymentTransactionDTO>> page(String order_id);
    Optional<Long> count(String order_id);
    Optional<PaymentTransactions> getPaymentTransaction(String stringFilter);
    Optional<List<PaymentTransactions>> updateStatusOrderIdAfter15Minutes();
}
