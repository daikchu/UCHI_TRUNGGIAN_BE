package com.vn.osp.notarialservices.identityAuthentication.dto;
import lombok.Data;

@Data
public class OutputTransactionSync {
    private boolean error;
    private String errorReason;
    private String toastMessage;
    private RefTransaction object;
}
