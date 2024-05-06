package com.vn.osp.notarialservices.identityAuthentication.dto;
import lombok.Data;

@Data
public class OutputGenerateVietQRCode {
    private String bankCode;
    private String bankName;
    private String bankAccount;
    private String userBankName;
    private String amount;
    private String content;
    private String qrCode;
    private String imgId;
    private Integer existing;
    private String transactionId;
    private String transactionRefId;
    private String qrLink;
    private String terminalCode;
    private String orderId;
    private byte[] imageQRCode;
}
