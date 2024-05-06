package com.vn.osp.notarialservices.identityAuthentication.dto;
import lombok.Data;

@Data
public class FormInputGenerateVietQR {
    private String orderId;
    private String amount;
    private Integer genQRTransType;
}
