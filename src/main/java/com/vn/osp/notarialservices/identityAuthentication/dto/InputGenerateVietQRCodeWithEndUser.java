package com.vn.osp.notarialservices.identityAuthentication.dto;
import lombok.Data;

@Data
public class InputGenerateVietQRCodeWithEndUser {
    private String bankAccount;
    private String amount;
    private String content;
    private String bankCode;
    private String transType;
    private String userBankName;
    private String orderId;
    private String sign;
    private String customerBankAccount;
    private String customerBankCode;
    private String customerName;

    public InputGenerateVietQRCodeWithEndUser(String bankAccount, String amount, String content, String bankCode, String transType, String userBankName, String orderId, String sign, String customerBankAccount, String customerBankCode, String customerName){
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.content = content;
        this.bankCode = bankCode;
        this.transType = transType;
        this.userBankName = userBankName;
        this.orderId = orderId;
        this.sign = sign;
        this.customerBankAccount = customerBankAccount;
        this.customerBankCode = customerBankCode;
        this.customerName = customerName;
    }
}
