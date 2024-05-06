package com.vn.osp.notarialservices.citizenInformation.domain;

import com.vn.osp.notarialservices.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "npo_citizen_info")
public class CitizenInfoBO{
    @Id
    private String cccd_number;
    private String cmnd_number;
    private String full_name;
    private Date date_of_birth;
    private String sex;
    private String country;
    private String ethnic;
    private String religion;
    private String hometown;
    private String permanent_address;
    private Date date_issuance;
    private Date date_expiration;
    private String identification_characteristics;
    private String avatar_img;
    private int verified_count;
    private java.sql.Timestamp entry_date_time;
    private java.sql.Timestamp update_date_time;
    private String entry_user_name;
    private String update_user_name;
}
