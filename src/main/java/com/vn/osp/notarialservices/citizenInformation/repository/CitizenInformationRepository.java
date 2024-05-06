package com.vn.osp.notarialservices.citizenInformation.repository;

import com.vn.osp.notarialservices.citizenInformation.domain.CitizenInfoBO;
import com.vn.osp.notarialservices.paymentTransaction.domain.OrderMapVerification;

import java.util.Optional;

public interface CitizenInformationRepository {
    Optional<CitizenInfoBO> saveOrUpdate(CitizenInfoBO citizenInfoBO);
    Optional<OrderMapVerification> getVerifyIdMapOrderId(String notary_office_code);
}
