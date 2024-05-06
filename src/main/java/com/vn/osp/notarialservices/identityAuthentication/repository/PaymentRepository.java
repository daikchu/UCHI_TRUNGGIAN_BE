package com.vn.osp.notarialservices.identityAuthentication.repository;

import com.vn.osp.notarialservices.common.repository.BaseRepository;
import com.vn.osp.notarialservices.identityAuthentication.domain.BankBeneficiaryBO;

public interface PaymentRepository extends BaseRepository<BankBeneficiaryBO,Long>, PaymentRepositoryCustom{
}
