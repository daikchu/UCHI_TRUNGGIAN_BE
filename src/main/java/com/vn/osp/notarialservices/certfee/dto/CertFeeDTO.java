package com.vn.osp.notarialservices.certfee.dto;

import lombok.Data;

@Data
public class CertFeeDTO {
    private Long id;
    private int type;
    private String name;
    private String description;
    private String formula_fee;
    private Long from_fee;
    private Long to_fee;
    private String circulars_fee;
}
