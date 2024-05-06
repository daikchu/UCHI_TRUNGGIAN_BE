package com.vn.osp.notarialservices.paymentTransaction.repository;

import com.vn.osp.notarialservices.paymentTransaction.domain.OrderMapVerification;
import com.vn.osp.notarialservices.paymentTransaction.domain.PaymentTransactions;
import com.vn.osp.notarialservices.paymentTransaction.dto.PaymentTransactionDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
@Transactional
public interface OrderMapVerifyRepository {
    Optional<OrderMapVerification> saveOrUpdate(OrderMapVerification orderMapVerification);
}
