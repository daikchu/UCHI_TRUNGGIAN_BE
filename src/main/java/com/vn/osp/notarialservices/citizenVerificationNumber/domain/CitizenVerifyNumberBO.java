package com.vn.osp.notarialservices.citizenVerificationNumber.domain;

import com.vn.osp.notarialservices.common.domain.BaseEntity;
import com.vn.osp.notarialservices.status.domain.StatusId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "npo_citizen_verification_numbers")
@IdClass(CitizenVerifyNumberId.class)
public class CitizenVerifyNumberBO{
    @Id
    private String notary_office_code;
    @Id
    private String province_code;
    private int verify_number_total;
    private String note;
    private java.sql.Timestamp entry_date_time;
    private java.sql.Timestamp update_date_time;
    private String entry_user_name;
    private String update_user_name;
}
