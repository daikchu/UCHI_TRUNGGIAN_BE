package com.vn.osp.notarialservices.certfee.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "npo_certificate_fee")
public class CertFeeBO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private int type;
    private String name;
    private String description;
    private String formula_fee;
    private Long from_fee;
    private Long to_fee;
    private String circulars_fee;

}
