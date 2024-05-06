package com.vn.osp.notarialservices.customer.service;


import com.vn.osp.notarialservices.common.converter.Converter;
import com.vn.osp.notarialservices.customer.domain.CustomerBO;
import com.vn.osp.notarialservices.customer.dto.CustomerDTO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerConverter implements Converter<CustomerBO, CustomerDTO> {

    @Override
    public CustomerDTO convert(final CustomerBO source) {
        return new CustomerDTO(
                source.getId(),
                source.getActive_flg(),
                source.getAddress(),
                source.getCode(),
                source.getEmail(),
                source.getMac_address(),
                source.getIp_address(),
                source.getName(),
                source.getOffice_type(),
                source.getPhone(),
                source.getWebsite(),
                source.getDomain(),
                source.getIs_demo(),
                source.getPakage_code(),
                convertTimeStampToString(source.getDate_start()),
                convertTimeStampToString(source.getDate_exp()),
                source.getMessage_show(),
                source.getMessage_time_to_show(),
                source.getMessage_active_flg(),
                source.getType(),
                source.getDescription(),
                source.getProvince_code(),
                source.getNote(),
                source.getCitizen_verify_package_code());

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

    public List<CustomerDTO> converts(final List<CustomerBO> source) {
        return source.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
        public CustomerBO convert(final CustomerDTO source) {
        CustomerBO customerBO = new CustomerBO();

        customerBO.setId(source.getNoid());
        customerBO.setActive_flg(source.getActive_flg());
        customerBO.setAddress(source.getAddress());
        customerBO.setCode(source.getCode());
        customerBO.setEmail(source.getEmail());
        customerBO.setMac_address(source.getMac_address());
        customerBO.setIp_address(source.getIp_address());
        customerBO.setName(source.getName());
        customerBO.setOffice_type(source.getOffice_type());
        customerBO.setPhone(source.getPhone());
        customerBO.setWebsite(source.getWebsite());
        customerBO.setDomain(source.getDomain());
        customerBO.setIs_demo(source.getIs_demo());
        customerBO.setPakage_code(source.getPakage_code());
        customerBO.setDate_start(convertStringToTimeStamp(source.getDate_start()));
        customerBO.setDate_exp(convertStringToTimeStamp(source.getDate_exp()));
        customerBO.setMessage_show(source.getMessage_show());
        customerBO.setMessage_time_to_show(source.getMessage_time_to_show());
        customerBO.setMessage_active_flg(source.getMessage_active_flg());
        customerBO.setType(source.getType());
        customerBO.setDescription(source.getDescription());
        customerBO.setProvince_code(source.getProvince_code());
        customerBO.setNote(source.getNote());
        customerBO.setCitizen_verify_package_code(source.getCitizen_verify_package_code());
        return customerBO;
    }

}
