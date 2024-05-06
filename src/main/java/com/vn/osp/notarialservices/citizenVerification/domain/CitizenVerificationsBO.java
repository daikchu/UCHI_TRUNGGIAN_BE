package com.vn.osp.notarialservices.citizenVerification.domain;

import com.vn.osp.notarialservices.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "npo_citizen_verifications")
public class CitizenVerificationsBO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "verify_id", nullable = false)
    private String verify_id;
    private String transaction_id;
    private String notary_office_id;
    private String province_code;
    private java.sql.Timestamp verify_date;
    private String verify_by;
    private String verify_status;
    private String citizen_verify_cccd;
    private String citizen_verify_fullname;
    private String citizen_info;
    private String verify_by_name;
    private java.sql.Timestamp entry_date_time;
    private java.sql.Timestamp update_date_time;
    private String entry_user_name;
    private String update_user_name;

}
