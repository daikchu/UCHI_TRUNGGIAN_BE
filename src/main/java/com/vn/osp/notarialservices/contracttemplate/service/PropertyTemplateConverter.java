package com.vn.osp.notarialservices.contracttemplate.service;

import com.vn.osp.notarialservices.common.converter.Converter;
import com.vn.osp.notarialservices.contracttemplate.domain.PropertyTemplateBO;
import com.vn.osp.notarialservices.contracttemplate.dto.PropertyTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TienManh on 8/5/2017.
 */
@Service
public class PropertyTemplateConverter implements Converter<PropertyTemplateBO, PropertyTemplate> {
    @Override
    public PropertyTemplate convert(PropertyTemplateBO source){
        return new PropertyTemplate(
                source.getId(),
                source.getType(),
                source.getName(),
                source.getDescription(),
                source.getHtml(),
                source.getEntry_user(),
                source.getEntry_time(),
                source.getUpdate_user(),
                source.getUpdate_time()
        );
    }
    @Override
    public PropertyTemplateBO convert(PropertyTemplate source){
        PropertyTemplateBO item=new PropertyTemplateBO();
        item.setId(source.getPid());
        item.setType(source.getType());
        item.setName(source.getName());
        item.setDescription(source.getDescription());
        item.setHtml(source.getHtml());
        item.setEntry_user(source.getEntry_user());
        item.setEntry_time(source.getEntry_time());
        item.setUpdate_user(source.getUpdate_user());
        item.setUpdate_time(source.getUpdate_time());
        return item;
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
}
