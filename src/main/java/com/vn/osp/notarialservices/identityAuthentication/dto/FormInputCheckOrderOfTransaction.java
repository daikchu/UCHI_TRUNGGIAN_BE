package com.vn.osp.notarialservices.identityAuthentication.dto;

import lombok.Data;

@Data
public class FormInputCheckOrderOfTransaction {
    private String bankAccount;
    private String orderId;
}
