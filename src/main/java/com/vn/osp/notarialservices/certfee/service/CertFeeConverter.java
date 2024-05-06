package com.vn.osp.notarialservices.certfee.service;

import com.vn.osp.notarialservices.certfee.domain.CertFeeBO;
import com.vn.osp.notarialservices.certfee.dto.CertFeeDTO;
import org.springframework.stereotype.Component;

@Component
public class CertFeeConverter {
    public CertFeeDTO convert(CertFeeBO item){
        CertFeeDTO result = new CertFeeDTO();
        result.setId(item.getId());
        result.setName(item.getName());
        result.setDescription(item.getDescription());
        result.setCirculars_fee(item.getCirculars_fee());
        result.setFormula_fee(item.getFormula_fee());
        result.setFrom_fee(item.getFrom_fee());
        result.setTo_fee(item.getTo_fee());
        result.setType(item.getType());
        return result;
    }

    public CertFeeBO convert(CertFeeDTO item){
        CertFeeBO result = new CertFeeBO();
        result.setId(item.getId());
        result.setName(item.getName());
        result.setDescription(item.getDescription());
        result.setCirculars_fee(item.getCirculars_fee());
        result.setFormula_fee(item.getFormula_fee());
        result.setFrom_fee(item.getFrom_fee());
        result.setTo_fee(item.getTo_fee());
        result.setType(item.getType());
        return result;
    }
}
