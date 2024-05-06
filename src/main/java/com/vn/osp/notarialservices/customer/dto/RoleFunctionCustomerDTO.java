package com.vn.osp.notarialservices.customer.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.vn.osp.notarialservices.function.dto.FunctionDTO;
import com.vn.osp.notarialservices.user.dto.GroupRole;

import java.util.List;

@XStreamAlias("GroupRoleFunctionTemplateDTO")
public class RoleFunctionCustomerDTO {
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
    private String date_start;
    private String date_exp;
    private String message_show;
    private Integer message_time_to_show;
    private Integer message_active_flg;
    private Integer type;
    private String description;
    private String province_code;
    private String note;

//    private GroupRole groupRole;

//    private List<FunctionDTO> notarySystemManagerListChoose;
//    private List<FunctionDTO> notaryfunctionListChoose;
//    private List<FunctionDTO> notaryreportListChoose;

    private String cb01;
    private String cb02;
    private String cb03;


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

    public String getCb01() {
        return cb01;
    }

    public void setCb01(String cb01) {
        this.cb01 = cb01;
    }

    public String getCb02() {
        return cb02;
    }

    public void setCb02(String cb02) {
        this.cb02 = cb02;
    }

    public String getCb03() {
        return cb03;
    }

    public void setCb03(String cb03) {
        this.cb03 = cb03;
    }
//    public GroupRole getGroupRole() {
//        return groupRole;
//    }
//
//    public void setGroupRole(GroupRole groupRole) {
//        this.groupRole = groupRole;
//    }


//    public List<FunctionDTO> getNotarySystemManagerListChoose() {
//        return notarySystemManagerListChoose;
//    }
//
//    public void setNotarySystemManagerListChoose(List<FunctionDTO> notarySystemManagerListChoose) {
//        this.notarySystemManagerListChoose = notarySystemManagerListChoose;
//    }
//
//    public List<FunctionDTO> getNotaryfunctionListChoose() {
//        return notaryfunctionListChoose;
//    }
//
//    public void setNotaryfunctionListChoose(List<FunctionDTO> notaryfunctionListChoose) {
//        this.notaryfunctionListChoose = notaryfunctionListChoose;
//    }
//
//    public List<FunctionDTO> getNotaryreportListChoose() {
//        return notaryreportListChoose;
//    }
//
//    public void setNotaryreportListChoose(List<FunctionDTO> notaryreportListChoose) {
//        this.notaryreportListChoose = notaryreportListChoose;
//    }
}
