package com.vn.osp.notarialservices.citizenVerification.service;

import com.vn.osp.notarialservices.citizenVerification.domain.CitizenVerificationsBO;
import com.vn.osp.notarialservices.citizenVerification.dto.CitizenVerificationsDTO;
import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderDTO;
import com.vn.osp.notarialservices.common.util.PagingResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CitizenVerficationService {
    Optional<CitizenVerificationsBO> saveOrUpdate(CitizenVerificationsBO citizenVerificationsBO);
    Optional<PagingResult> page(int page,
                                int numberPerPage,
                                String verify_id,
                                String province_code,
                                String notary_office_id,
                                String verify_status,
                                String verify_by,
                                String citizen_verify_fullname,
                                String citizen_verify_cccd,
                                String verify_date_from,
                                String verify_date_to,
                                String order_id, String verify_by_name);
    Optional<Long> count(String verify_id,
                         String province_code,
                         String notary_office_id,
                         String verify_status,
                         String verify_by,
                         String citizen_verify_fullname,
                         String citizen_verify_cccd,
                         String verify_date_from,
                         String verify_date_to,
                         String order_id, String verify_by_name);
    Optional<CitizenVerificationsDTO> getDetail(String verify_id);
    Optional<List<CitizenVerificationsDTO>> filter(String verify_id,
                                                   String province_code,
                                                   String notary_office_id,
                                                   String verify_status,
                                                   String verify_by,
                                                   String citizen_verify_fullname,
                                                   String citizen_verify_cccd,
                                                   String verify_date_from,
                                                   String verify_date_to,
                                                   String order_id, String verify_by_name);
    Optional<List<Map>> retrieveUserAccountsFromData(String province_code, String notary_office_code);
    Optional<List<CitizenVerificationsDTO>> getAllPurchased(String order_id);
}
