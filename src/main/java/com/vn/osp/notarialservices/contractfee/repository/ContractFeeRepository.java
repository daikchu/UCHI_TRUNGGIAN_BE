package com.vn.osp.notarialservices.contractfee.repository;

import com.vn.osp.notarialservices.common.repository.BaseRepository;
import com.vn.osp.notarialservices.contractfee.domain.ContractFeeBO;

/**
 * Created by Admin on 2018-05-09.
 */
public interface ContractFeeRepository extends BaseRepository<ContractFeeBO,Long>,ContractFeeRepositoryCustom{
}
