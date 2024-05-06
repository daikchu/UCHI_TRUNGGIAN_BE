package com.vn.osp.notarialservices.citizenInformation.service;

import com.vn.osp.notarialservices.citizenInformation.domain.CitizenInfoBO;
import com.vn.osp.notarialservices.citizenInformation.dto.CitizenInformationDTO;
import com.vn.osp.notarialservices.paymentTransaction.domain.OrderMapVerification;

import java.util.Optional;

public interface CitizenInformationService {
    Optional<CitizenInformationDTO> saveOrUpdate(CitizenInformationDTO citizenInformationDTO);
    OrderMapVerification generateVerifyId(String notary_office_code);
}
