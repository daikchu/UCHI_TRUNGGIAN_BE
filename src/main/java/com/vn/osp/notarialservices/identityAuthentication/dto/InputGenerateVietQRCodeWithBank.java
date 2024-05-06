package com.vn.osp.notarialservices.identityAuthentication.dto;
import lombok.Data;

@Data
public class InputGenerateVietQRCodeWithBank {
    private String bankAccount;
    private String amount;
    private String content;
    private String bankCode;
    private String transType;
    private String userBankName;
    private String orderId;
    private String sign;

    public InputGenerateVietQRCodeWithBank(String bankAccount, String amount, String content, String bankCode, String transType, String userBankName, String orderId, String sign){
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.content = content;
        this.bankCode = bankCode;
        this.transType = transType;
        this.userBankName = userBankName;
        this.orderId = orderId;
        this.sign = sign;
    }
}
