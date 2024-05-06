package com.vn.osp.notarialservices.identityAuthentication.dto;

import lombok.Data;

@Data
public class ResultTokenGenerate {
    private long timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
