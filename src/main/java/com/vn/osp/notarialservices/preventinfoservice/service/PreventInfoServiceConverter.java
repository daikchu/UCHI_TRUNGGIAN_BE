package com.vn.osp.notarialservices.preventinfoservice.service;

import com.vn.osp.notarialservices.common.converter.Converter;
import com.vn.osp.notarialservices.preventinfoservice.domain.PreventInfoServiceBo;
import com.vn.osp.notarialservices.preventinfoservice.dto.PreventInfoService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Admin on 12/6/2017.
 */
@Service
public class PreventInfoServiceConverter implements Converter<PreventInfoServiceBo,PreventInfoService> {
    @Override
    public PreventInfoService convert(final PreventInfoServiceBo source) {
        return new PreventInfoService (
                source.getPid(),
                source.getHost(),
                source.getPost(),
                source.getServicename(),
                source.getMethod(),
                source.getProvincecode());


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
    public PreventInfoServiceBo convert(final PreventInfoService source) {
        PreventInfoServiceBo preventInfoServiceBo = new PreventInfoServiceBo();
        preventInfoServiceBo.setPid(source.getPid());
        preventInfoServiceBo.setHost(source.getHost());
        preventInfoServiceBo.setPost(source.getPost());
        preventInfoServiceBo.setServicename(source.getServicename());
        preventInfoServiceBo.setMethod(source.getMethod());
        preventInfoServiceBo.setProvincecode(source.getProvincecode());



        return preventInfoServiceBo;
    }
}
