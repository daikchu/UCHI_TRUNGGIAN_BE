package com.vn.osp.notarialservices.certfee.service;

import com.vn.osp.notarialservices.certfee.dto.CertFeeDTO;

import java.util.List;

public interface CertFeeService {
    CertFeeDTO getByType(int type);
    List<CertFeeDTO> search(String filter);
}
