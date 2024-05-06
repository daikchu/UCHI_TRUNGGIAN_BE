package com.vn.osp.notarialservices.identityAuthentication.dto;

import lombok.Data;

@Data
public class OutputCheckOrder {
    private long amount;
    private String transType;
    private String orderId;
    private String referenceNumber;
    private long timeCreated;
    private long timePaid;
    private int status;
    private int type;
    private String content;
}
