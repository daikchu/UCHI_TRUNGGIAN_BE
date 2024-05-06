package com.vn.osp.notarialservices.citizenVerificationOrder.service;

import com.vn.osp.notarialservices.citizenVerificationOrder.domain.CitizenVerifyOrderBO;
import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderDTO;
import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderSum;
import com.vn.osp.notarialservices.common.util.PagingResult;
import com.vn.osp.notarialservices.paymentTransaction.dto.PaymentTransactionDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CitizenVerifyOrderService {
    Optional<CitizenVerifyOrderDTO> insert(CitizenVerifyOrderDTO citizenVerifyOrderDTO);
    Optional<CitizenVerifyOrderDTO> update(CitizenVerifyOrderDTO citizenVerifyOrderDTO, boolean isCreateTransactionLog);
    Optional<Boolean> updateBySystem(String order_id, String transaction_status, String status, String notary_fee_received, String payment_content, String note);
    Optional<Boolean> updateByChangeTransaction(PaymentTransactionDTO paymentTransactionDTO);
    Optional<Boolean> deleteById(Long order_id);
    Optional<CitizenVerifyOrderDTO> getDetail(String order_id);
    Optional<List<CitizenVerifyOrderDTO>> filter(String order_id, String notary_office_code,
                          String province_code, String transaction_status, String status,
                          String update_by, String order_time_from, String order_time_to, String update_user_name);
    Optional<PagingResult> page(int page, int numberPerPage, String order_id, String notary_office_code,
                                String province_code, String transaction_status, String status,
                                String update_by_officer, String order_time_from, String order_time_to, String update_user_name);
    Optional<Long> count(String order_id, String notary_office_code,
                            String province_code, String transaction_status, String status,
                            String update_by, String order_time_from, String order_time_to, String update_user_name);
    Optional<List<CitizenVerifyOrderBO>> getCitizenVerifyOrderByFilter(String stringFilter);

    Optional<Map> getSumCitizenVerifyOrder(String order_id, String notary_office_code,
                                                             String province_code, String transaction_status, String status,
                                                             String update_by, String order_time_from, String order_time_to, String update_user_name);

    Optional<List<Map>> retrieveUserAccountsFromData(String province_code, String notary_office_code);
}

