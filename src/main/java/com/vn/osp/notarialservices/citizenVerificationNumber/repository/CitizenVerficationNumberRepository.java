package com.vn.osp.notarialservices.citizenVerificationNumber.repository;

import com.vn.osp.notarialservices.citizenVerificationNumber.domain.CitizenVerifyNumberBO;
import com.vn.osp.notarialservices.citizenVerificationNumber.dto.CitizenVerifyNumberDTO;

import java.util.List;
import java.util.Optional;

public interface CitizenVerficationNumberRepository {
    Optional<CitizenVerifyNumberBO> saveOrUpdate(CitizenVerifyNumberBO citizenVerifyNumberBO, boolean isPlusTurn);
    Optional<List<CitizenVerifyNumberBO>> getCitizenVerifyNumberByFilter(String stringFilter);
    Optional<CitizenVerifyNumberBO> get(String notaryOfficeCode, String provinceCode);
    Optional<CitizenVerifyNumberDTO> getDetail(String notaryOfficeCode, String provinceCode);
    List<CitizenVerifyNumberBO> checkNotaryOfficeCodeExits(String notary_office_code, String province_code);
}
