package com.vn.osp.notarialservices.citizenVerifyPackage.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "npo_citizen_verify_packages")
public class CitizenVerifyPackageBO {
    @Id
    private String code;
    private String name;
    private String verify_type;
    private String description;
    private String price;
    private String note;
}
