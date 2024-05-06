package com.vn.osp.notarialservices.contractkind.repository;

import com.vn.osp.notarialservices.common.repository.BaseRepository;
import com.vn.osp.notarialservices.contractkind.domain.ContractKindBo;

/**
 * Created by minh on 5/30/2017.
 */
public interface ContractKindRepository extends BaseRepository<ContractKindBo,Long>,ContractKindRepositoryCustom {
}
