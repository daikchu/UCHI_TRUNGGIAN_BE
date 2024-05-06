package com.vn.osp.notarialservices.paymentTransaction.repository;

import com.vn.osp.notarialservices.paymentTransaction.domain.PaymentTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransactions, Long>, PaymentTransactionRepositoryCustom {

    @Query(value = "SELECT * FROM npo_payment_transactions ps WHERE ps.transaction_id = ?1", nativeQuery = true)
    PaymentTransactions findFirstByTransaction_id(String transaction_id);

    Optional<PaymentTransactions>  saveOrUpdateTransactionBO(PaymentTransactions paymentTransactions);
    Boolean checkExitsRefTransactionId(String refTransactionId);
    Optional<PaymentTransactions> getPaymentTransaction(String stringFilter);
    Optional<List<PaymentTransactions>> updateStatusOrderIdAfter15Minutes();
}
