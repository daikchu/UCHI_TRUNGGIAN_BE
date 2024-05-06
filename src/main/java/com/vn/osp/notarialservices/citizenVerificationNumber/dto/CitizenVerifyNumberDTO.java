package com.vn.osp.notarialservices.citizenVerificationNumber.dto;

import lombok.Data;

@Data
public class CitizenVerifyNumberDTO {
    private String notary_office_code;
    private String notary_office_name;
    private String province_code;
    private Long verify_number_total;
    private String note;
}
