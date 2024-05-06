package com.vn.osp.notarialservices.identityAuthentication.dto;
import lombok.Data;

@Data
public class ResultCode {
    private String status;
    private String message;
    private OutputGenerateVietQRCode data;
}
