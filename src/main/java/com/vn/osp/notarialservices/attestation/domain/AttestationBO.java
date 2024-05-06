package com.vn.osp.notarialservices.attestation.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "npo_attestation_template")
public class AttestationBO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    private String code;
    @Column(name = "kind_html")
    private String kind_html;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private Integer type;
    @Column(name = "active_flg")
    private Integer active_flg;
    @Column(name = "type_org")
    private Integer type_org;
    private String parent_code;
    private Long entry_user_id;
    @Column(name = "entry_user_name")
    private String entry_user_name;
    @Column(name = "entry_date_time")
    private Date entry_date_time;
    @Column(name = "update_user_id")
    private Long update_user_id;
    @Column(name = "update_user_name")
    private String update_user_name;
    @Column(name = "update_date_time")
    private Date update_date_time;
}
