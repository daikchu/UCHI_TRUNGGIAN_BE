package com.vn.osp.notarialservices.identityAuthentication.repository;

import com.vn.osp.notarialservices.identityAuthentication.domain.BankBeneficiaryBO;

import java.util.List;
import java.util.Optional;

public interface PaymentRepositoryCustom {
    Optional<List<BankBeneficiaryBO>> getListBankBeneficiary(Integer genQRTransType);
}
