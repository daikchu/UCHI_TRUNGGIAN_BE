package com.vn.osp.notarialservices.identityAuthentication.dto;

import lombok.Data;

@Data
public class FormInputCheckOrder {
    private String bankAccount;
    private int type;
    private String value;
    private String checkSum;
}
