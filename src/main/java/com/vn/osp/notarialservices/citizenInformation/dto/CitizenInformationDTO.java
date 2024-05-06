package com.vn.osp.notarialservices.citizenInformation.dto;

import com.vn.osp.notarialservices.citizenInformation.domain.CitizenInfoBO;
import com.vn.osp.notarialservices.paymentTransaction.domain.OrderMapVerification;
import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
public class CitizenInformationDTO {
    private String verify_id;
    private String notary_office_id;
    private String province_code;
    private java.sql.Timestamp verify_date;
    private String verify_by;
    private String verify_by_name;
    private String verify_status;

    private String cccd_number;
    private String cmnd_number;
    private String full_name;
    private String date_of_birth;
    private String sex;
    private String country;
    private String ethnic;
    private String religion;
    private String hometown;
    private String permanent_address;
    private String date_issuance;
    private String date_expiration;
    private String identification_characteristics;
    private String avatar_img;
    private int verified_count;
    private String citizen_info;
    private String minus_verify_number_yn;
    // kiểm tra là còn lượt xác thực hay không
    private boolean haveVerifyNumber;
    private OrderMapVerification order_map_verification;

    public CitizenInfoBO convertToCitizenInfoBO(CitizenInformationDTO dto) throws ParseException {
        CitizenInfoBO citizenInfoBO = new CitizenInfoBO();
        citizenInfoBO.setCccd_number(dto.getCccd_number());
        citizenInfoBO.setCmnd_number(dto.getCmnd_number());
        citizenInfoBO.setFull_name(dto.getFull_name());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if(dto.getDate_of_birth() != null && !dto.getDate_of_birth().equals("")){
            citizenInfoBO.setDate_of_birth(df.parse(dto.getDate_of_birth()));
        }
        citizenInfoBO.setSex(dto.getSex());
        citizenInfoBO.setCountry(dto.getCountry());
        citizenInfoBO.setEthnic(dto.getEthnic());
        citizenInfoBO.setReligion(dto.getReligion());
        citizenInfoBO.setHometown(dto.getHometown());
        citizenInfoBO.setPermanent_address(dto.getPermanent_address());
        if(dto.getDate_issuance() != null && !dto.getDate_issuance().equals("")){
            citizenInfoBO.setDate_issuance(df.parse(dto.getDate_issuance()));
        }
        if(dto.getDate_expiration() != null && !dto.getDate_expiration().equals("")){
            citizenInfoBO.setDate_expiration(df.parse(dto.getDate_expiration()));
        }
        citizenInfoBO.setIdentification_characteristics(dto.getIdentification_characteristics());
        citizenInfoBO.setAvatar_img(dto.getAvatar_img());
        citizenInfoBO.setVerified_count(dto.getVerified_count());
        return citizenInfoBO;
    }
}
