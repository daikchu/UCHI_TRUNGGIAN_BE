package com.vn.osp.notarialservices.attestation.service;

import com.vn.osp.notarialservices.attestation.domain.AttestationBO;
import com.vn.osp.notarialservices.attestation.dto.AttestationDTO;
import org.springframework.stereotype.Service;

@Service
public class AttestationTemplateConverter {
    public AttestationBO convert(AttestationDTO item){
        AttestationBO bo = new AttestationBO();
        bo.setId(item.getId());
        bo.setActive_flg(item.getActive_flg());
        bo.setDescription(item.getDescription());
        bo.setName(item.getName());
        bo.setCode(item.getCode());
        bo.setKind_html(item.getKind_html());
        bo.setType(item.getType());
        bo.setType_org(item.getType_org());
        bo.setParent_code(item.getParent_code());
        bo.setEntry_date_time(item.getEntry_date_time());
        bo.setEntry_user_id(item.getEntry_user_id());
        bo.setEntry_user_name(item.getEntry_user_name());
        bo.setUpdate_date_time(item.getUpdate_date_time());
        bo.setUpdate_user_id(item.getUpdate_user_id());
        bo.setUpdate_user_name(item.getUpdate_user_name());
        return bo;
    }

    public AttestationDTO convert(AttestationBO item){
        AttestationDTO result = new AttestationDTO();
        result.setId(item.getId());
        result.setActive_flg(item.getActive_flg());
        result.setDescription(item.getDescription());
        result.setName(item.getName());
        result.setCode(item.getCode());
        result.setKind_html(item.getKind_html());
        result.setType(item.getType());
        result.setType_org(item.getType_org());
        result.setParent_code(item.getParent_code());
        result.setEntry_date_time(item.getEntry_date_time());
        result.setEntry_user_id(item.getEntry_user_id());
        result.setEntry_user_name(item.getEntry_user_name());
        result.setUpdate_date_time(item.getUpdate_date_time());
        result.setUpdate_user_id(item.getUpdate_user_id());
        result.setUpdate_user_name(item.getUpdate_user_name());
        return result;
    }
}
