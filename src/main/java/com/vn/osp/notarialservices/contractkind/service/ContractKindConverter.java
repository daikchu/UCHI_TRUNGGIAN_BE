package com.vn.osp.notarialservices.contractkind.service;

import com.vn.osp.notarialservices.common.converter.Converter;
import com.vn.osp.notarialservices.contractkind.domain.ContractKindBo;
import com.vn.osp.notarialservices.contractkind.dto.ContractKind;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by minh on 5/30/2017.
 */
@Service
public class ContractKindConverter implements Converter<ContractKindBo,ContractKind> {
    @Override
    public ContractKind convert(final ContractKindBo source) {
        return new ContractKind (
                source.getCkId(),
                source.getName(),
                source.getParent_kind_id(),
                source.getOrder_number(),
                source.getEntry_user_id(),
                source.getEntry_user_name(),
                convertTimeStampToString(source.getEntry_date_time()),
                source.getUpdate_user_id(),
                source.getUpdate_user_name(),
                convertTimeStampToString(source.getUpdate_date_time()),
                source.getContract_kind_code());

    }
    public Timestamp convertStringToTimeStamp(String dateString){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date parsedDate = null;
            parsedDate = dateFormat.parse(dateString);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            return  timestamp;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String convertTimeStampToString(Timestamp date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        if(date==null){
            return null;
        }
        else {
            String result = dateFormat.format(date);
            return result;
        }
    }
    @Override
    public ContractKindBo convert(final ContractKind source) {
        ContractKindBo contractKindBo = new ContractKindBo();
        contractKindBo.setCkId(source.getCkId());
        contractKindBo.setName(source.getName());
        contractKindBo.setOrder_number(source.getOrder_number());
        contractKindBo.setEntry_user_id(source.getEntry_user_id());
        contractKindBo.setEntry_user_name(source.getEntry_user_name());
        contractKindBo.setEntry_date_time(convertStringToTimeStamp(source.getEntry_date_time()));
        contractKindBo.setUpdate_user_id(source.getUpdate_user_id());
        contractKindBo.setUpdate_user_name(source.getUpdate_user_name());
        contractKindBo.setUpdate_date_time(convertStringToTimeStamp(source.getUpdate_date_time()));
        contractKindBo.setContract_kind_code(source.getContract_kind_code());



        return contractKindBo;
    }

}
