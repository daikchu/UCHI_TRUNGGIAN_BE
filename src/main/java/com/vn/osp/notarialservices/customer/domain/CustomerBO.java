package com.vn.osp.notarialservices.customer.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "npo_customer")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "osp_npo_customer_insert",
                procedureName = "osp_npo_customer_insert",
                parameters = {
                        @StoredProcedureParameter(name = "xml_content", type = String.class, mode = ParameterMode.IN),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "osp_npo_customer_update",
                procedureName = "osp_npo_customer_update",
                resultClasses = { CustomerBO.class },
                parameters = {
                        @StoredProcedureParameter(name = "xml_content", type = String.class, mode = ParameterMode.IN),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "osp_npo_customer_delete_by_id",
                procedureName = "osp_npo_customer_delete_by_id",
                parameters = {
                        @StoredProcedureParameter(name = "noid", type = Long.class, mode = ParameterMode.IN),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "osp_npo_customer_count_total",
                procedureName = "osp_npo_customer_count_total",
                parameters = {
                        @StoredProcedureParameter(name = "stringFilter", type = String.class, mode = ParameterMode.IN),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "osp_npo_customer_select_by_filter",
                procedureName = "osp_npo_customer_select_by_filter",
                resultClasses = { CustomerBO.class },
                parameters = {
                        @StoredProcedureParameter(name = "stringFilter", type = String.class, mode = ParameterMode.IN),
                }
        ),
})
public class CustomerBO {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="noid", nullable = false)
    private Long id;

    @Column(name = "active_flg")
    private Integer active_flg;

    @Column(name = "address")
    private String address;

    @Column(name = "code")
    private String code;

    @Column(name = "email")
    private String email;

    @Column(name = "mac_address")
    private String mac_address;

    @Column(name = "ip_address")
    private String ip_address;

    @Column(name = "name")
    private String name;

    @Column(name = "office_type")
    private Integer office_type;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;

    @Column(name = "domain")
    private String domain;

    @Column(name = "is_demo")
    private Integer is_demo;

    @Column(name = "pakage_code")
        private String pakage_code;

    @Column(name="citizen_verify_package_code")
    private String citizen_verify_package_code;

    @Column(name = "date_start")
    private java.sql.Timestamp date_start;

    @Column(name = "date_exp")
    private java.sql.Timestamp date_exp;

    @Column(name = "message_show")
    private String message_show;

    @Column(name = "message_time_to_show")
    private Integer message_time_to_show;

    @Column(name = "message_active_flg")
    private Integer message_active_flg;

    @Column(name = "type")
    private Integer type;

    @Column(name = "description")
    private String description;

    @Column(name = "province_code")
    private String province_code;

    @Column(name = "note")
    private String note;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActive_flg() {
        return active_flg;
    }

    public void setActive_flg(Integer active_flg) {
        this.active_flg = active_flg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOffice_type() {
        return office_type;
    }

    public void setOffice_type(Integer office_type) {
        this.office_type = office_type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getIs_demo() {
        return is_demo;
    }

    public void setIs_demo(Integer is_demo) {
        this.is_demo = is_demo;
    }

    public String getPakage_code() {
        return pakage_code;
    }

    public void setPakage_code(String pakage_code) {
        this.pakage_code = pakage_code;
    }

    public String getCitizen_verify_package_code() {
        return citizen_verify_package_code;
    }

    public void setCitizen_verify_package_code(String citizen_verify_package_code) {
        this.citizen_verify_package_code = citizen_verify_package_code;
    }

    public Timestamp getDate_start() {
        return date_start;
    }

    public void setDate_start(Timestamp date_start) {
        this.date_start = date_start;
    }

    public Timestamp getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(Timestamp date_exp) {
        this.date_exp = date_exp;
    }

    public String getMessage_show() {
        return message_show;
    }

    public void setMessage_show(String message_show) {
        this.message_show = message_show;
    }

    public Integer getMessage_time_to_show() {
        return message_time_to_show;
    }

    public void setMessage_time_to_show(Integer message_time_to_show) {
        this.message_time_to_show = message_time_to_show;
    }

    public Integer getMessage_active_flg() {
        return message_active_flg;
    }

    public void setMessage_active_flg(Integer message_active_flg) {
        this.message_active_flg = message_active_flg;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
