package com.vn.osp.notarialservices.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

/**
 * Created by nmha on 3/24/2017.
 */
public class StringUtils {
    private static final Logger logger = Logger.getLogger(StringUtils.class);

    private static ObjectMapper om;

    public static ObjectMapper getObjectMapper() {
        if (om == null) {
            om = new ObjectMapper();
        }
        return om;
    }

    public static String getJson(Object object) {
        ObjectMapper mapper = getObjectMapper();
        if (object != null) {
            try {
                return mapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                logger.error("Get JSON string error: " + e.getMessage());
                return null;
            }
        }else{
            return null;
        }
    }

    public static boolean isExistInListString(String strCheck, String...strs){
        if(!org.apache.commons.lang3.StringUtils.isBlank(strCheck)) return false;
        for (String str : strs) {
            if(strCheck.equals(str)) return true;
        }
        return false;
    }
}
