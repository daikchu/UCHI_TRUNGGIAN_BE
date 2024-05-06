package com.vn.osp.notarialservices.customer.dto;

import com.vn.osp.notarialservices.citizenVerificationNumber.domain.CitizenVerifyNumberBO;
import com.vn.osp.notarialservices.citizenVerifyPackage.domain.CitizenVerifyPackageBO;
import com.vn.osp.notarialservices.function.dto.PackageFunctionDTO;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CustomerInfoDTO extends ResourceSupport {

    List<PackageFunctionDTO> functionDTOList;

    private String code;
    private String name;
    private String website;
    private String domain;
    private String pakage_code;
    private String citizen_verify_package_code;
    private java.sql.Timestamp date_start;
    private java.sql.Timestamp date_exp;
    private String message_show;
    private Integer message_time_to_show;
    private Integer message_active_flg;
    private String province_code;
    private CitizenVerifyPackageBO citizen_verify_package;
    private CitizenVerifyNumberBO citizenVerifyNumber;
   }
