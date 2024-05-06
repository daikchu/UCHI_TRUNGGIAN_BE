package com.vn.osp.notarialservices.notaryOffice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "npo_notary_office")
public class NotaryOfficeBO {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    private String name;
    private String code;
    private String address;
    private String phone;
    private String email;
    private String mac_address;
    private String ip_address;
    private Date date_exp;
    private Integer active_flg;
    private String description;

}
