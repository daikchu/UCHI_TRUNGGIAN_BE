package com.vn.osp.notarialservices.status.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "npo_status")
@IdClass(StatusId.class)
@Data
public class StatusBO {
    @Id
    private String code;
    @Id
    private String type;

    private String name;
    private String description;

}
