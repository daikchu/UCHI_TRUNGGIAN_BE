package com.vn.osp.notarialservices.identityAuthentication.dto;
import lombok.Data;

@Data
public class FormInputTransactionSync {
    private String transactionid;
    private Long transactiontime;
    private String referencenumber;
    private Integer amount;
    private String content;
    private String bankaccount;
    private String transType;
    private String orderId;
}
