package com.vn.osp.notarialservices.identityAuthentication.service;

import com.vn.osp.notarialservices.identityAuthentication.domain.BankBeneficiaryBO;
import com.vn.osp.notarialservices.identityAuthentication.dto.ErrorCode;
import com.vn.osp.notarialservices.identityAuthentication.dto.FormInputTransactionSync;
import com.vn.osp.notarialservices.paymentTransaction.domain.PaymentTransactions;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Optional<List<BankBeneficiaryBO>> getListBankBeneficiary(Integer genQRTransType);
    Optional<String> genRefTransactionId();
    Optional<ErrorCode> validateInputTransactionSync(FormInputTransactionSync formInputTransactionSync);
    Optional<ErrorCode> validateExitsTransaction(FormInputTransactionSync formInputTransactionSync, PaymentTransactions paymentTransaction);
    Optional<PaymentTransactions> handleTransactionSync(FormInputTransactionSync formInputTransactionSync,PaymentTransactions paymentTranStatusPending, PaymentTransactions paymentTransactions);
}
