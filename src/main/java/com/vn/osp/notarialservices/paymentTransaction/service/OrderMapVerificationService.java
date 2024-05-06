package com.vn.osp.notarialservices.paymentTransaction.service;

import com.vn.osp.notarialservices.paymentTransaction.domain.OrderMapVerification;

import java.util.List;
import java.util.Optional;

public interface OrderMapVerificationService {
    Optional<OrderMapVerification> saveOrUpdate(OrderMapVerification orderMapVerification);
    List<OrderMapVerification> orderIdMapVerification(String orderId, int verifyNumber);
}
