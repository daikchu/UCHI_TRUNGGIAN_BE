package com.vn.osp.notarialservices.bankPayment.repository;

import com.vn.osp.notarialservices.bankPayment.domain.BankPaymentBO;
import com.vn.osp.notarialservices.common.repository.BaseRepository;

/**
 * Created by Admin on 30/5/2017.
 */
public interface BankPaymentRepository extends BaseRepository<BankPaymentBO,Long>, BankPaymentRepositoryCustom {
}
