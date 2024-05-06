package com.vn.osp.notarialservices.citizenVerificationNumber.service;

import com.vn.osp.notarialservices.citizenVerificationNumber.domain.CitizenVerifyNumberBO;

import java.util.List;
import java.util.Optional;

public interface CitizenVerficationNumberService {
    Optional<CitizenVerifyNumberBO> saveOrUpdate(CitizenVerifyNumberBO citizenVerifyNumberBO, boolean isPlusTurn);
}
