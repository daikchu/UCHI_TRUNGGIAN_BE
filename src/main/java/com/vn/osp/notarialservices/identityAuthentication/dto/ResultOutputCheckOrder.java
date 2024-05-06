package com.vn.osp.notarialservices.identityAuthentication.dto;

import lombok.Data;

@Data
public class ResultOutputCheckOrder {
    private String status;
    private String message;
    private OutputCheckOrder[] data;
}
