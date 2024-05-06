package com.vn.osp.notarialservices.customer.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.vn.osp.notarialservices.common.dto.BaseEntityBeans;

import java.util.Date;
@XStreamAlias("CustomerDTO")
public class CustomerDTO extends BaseEntityBeans {
    private Long noid;
    private Integer active_flg;
    private String address;
    private String code;
    private String email;
    private String mac_address;
    private String ip_address;
    private String name;
    private Integer office_type;
    private String phone;
    private String website;
    private String domain;
    private Integer is_demo;
    private String pakage_code;
    private String citizen_verify_package_code;
    private String date_start;
    private String date_exp;
    private String message_show;
    private Integer message_time_to_show;
    private Integer message_active_flg;
    private Integer type;
    private String description;
    private String province_code;
    private String note;

    @JsonCreator
    public CustomerDTO(@JsonProperty(value = "noid", required = false) Long noid,
                       @JsonProperty(value = "active_flg", required = false) Integer active_flg,
                       @JsonProperty(value = "address", required = false) String address,
                       @JsonProperty(value = "code", required = false) String code,
                       @JsonProperty(value = "email", required = false) String email,
                       @JsonProperty(value = "mac_address", required = false) String mac_address,
                       @JsonProperty(value = "ip_address", required = false) String ip_address,
                       @JsonProperty(value = "name", required = false) String name,
                       @JsonProperty(value = "office_type", required = false) Integer office_type,
                       @JsonProperty(value = "phone", required = false) String phone,
                       @JsonProperty(value = "website", required = false) String website,
                       @JsonProperty(value = "domain", required = false) String domain,
                       @JsonProperty(value = "is_demo", required = false) Integer is_demo,
                       @JsonProperty(value = "pakage_code", required = false) String pakage_code,
                       @JsonProperty(value = "date_start", required = false) String date_start,
                       @JsonProperty(value = "date_exp", required = false) String date_exp,
                       @JsonProperty(value = "message_show", required = false) String message_show,
                       @JsonProperty(value = "message_time_to_show", required = false) Integer message_time_to_show,
                       @JsonProperty(value = "message_active_flg", required = false) Integer message_active_flg,
                       @JsonProperty(value = "type", required = false) Integer type,
                       @JsonProperty(value = "description", required = false) String description,
                       @JsonProperty(value = "province_code", required = false) String province_code,
                       @JsonProperty(value = "note", required = false) String note,
                       @JsonProperty(value = "citizen_verify_package_code", required = false) String citizen_verify_package_code
                       ){
        this.noid = noid;
        this.active_flg = active_flg;
        this.address = address;
        this.code = code;
        this.email = email;
        this.mac_address = mac_address;
        this.ip_address = ip_address;
        this.name = name;
        this.office_type = office_type;
        this.phone = phone;
        this.website = website;
        this.domain = domain;
        this.is_demo = is_demo;
        this.pakage_code = pakage_code;
        this.date_start = date_start;
        this.date_exp = date_exp;
        this.message_show = message_show;
        this.message_time_to_show = message_time_to_show;
        this.message_active_flg = message_active_flg;
        this.type = type;
        this.description = description;
        this.province_code =province_code;
        this.note = note;
        this.citizen_verify_package_code = citizen_verify_package_code;
    }


    public CustomerDTO(){

    }
//    public CustomerDTO(Long noid, Integer active_flg, String address, String code, String email, String mac_address, String ip_address,
//                       String name, Integer office_type, String phone, String website, String domain, Integer is_demo, String pakage_code,
//                       String date_start, String date_exp, String message_show, Integer message_time_to_show, Integer message_active_flg,
//                       Integer type, String description, String province_code, String note){
//        this.noid = noid;
//        this.active_flg = active_flg;
//        this.address = address;
//        this.code = code;
//        this.email = email;
//        this.mac_address = mac_address;
//        this.ip_address = ip_address;
//        this.name =name;
//        this.office_type = office_type;
//        this.phone = phone;
//        this.website = website;
//        this.domain = domain;
//        this.is_demo = is_demo;
//        this.pakage_code = pakage_code;
//        this.date_start = date_start;
//        this.date_exp = date_exp;
//        this.message_show = message_show;
//        this.message_time_to_show = message_time_to_show;
//        this.message_active_flg = message_active_flg;
//        this.type = type;
//        this.description = description;
//        this.province_code =province_code;
//        this.note = note;
//    }

    public Long getNoid() {
        return noid;
    }

    public void setNoid(Long noid) {
        this.noid = noid;
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

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(String date_exp) {
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
