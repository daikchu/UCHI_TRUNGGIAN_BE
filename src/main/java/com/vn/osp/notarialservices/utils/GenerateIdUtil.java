package com.vn.osp.notarialservices.utils;

import java.util.HashMap;
import java.util.Map;

public class GenerateIdUtil {
    public static String convertNumberToStringOrderIdNumber(int id_number){
        String str_id_number = "";
        int count_character =  String.valueOf(id_number).length();
        switch (count_character){
            case 1:
                str_id_number = "00000" + id_number;
                break;
            case 2:
                str_id_number = "0000" + id_number;
                break;
            case 3:
                str_id_number = "000" + id_number;
                break;
            case 4:
                str_id_number = "00" + id_number;
                break;
            case 5:
                str_id_number = "0" + id_number;
                break;
            default:
                str_id_number = String.valueOf(id_number);
        }
        return str_id_number;
    }
    public static <K,V> K getKeyByValue(Map<K,V> map, V valueToFind) {
        for (Map.Entry<K,V> entry : map.entrySet()) {
            if (valueToFind.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null; // Trả về null nếu không tìm thấy key tương ứng với value
    }

    public static String generateId(String predix, int dividend){
        String result = "";
        int divisor = 999999;  // Số chia

        int quotient = dividend / divisor; // thương
        int residual = dividend % divisor; // Phần dư

        Map<String, Integer> stringMap = new HashMap<>();
        int indexChar = 0;
        for (char char1 = 'A'; char1 <= 'Z'; char1++) {
            for (char char2 = 'A'; char2 <= 'Z'; char2++) {
                stringMap.put(""+char1 + char2+"", indexChar);
                indexChar++;
            }
        }
        if(residual != 0){
            String two_characters = getKeyByValue(stringMap, quotient);
            result = predix + two_characters + convertNumberToStringOrderIdNumber(residual);
        }else {
            String two_characters = getKeyByValue(stringMap, quotient-1);
            result = predix + two_characters + convertNumberToStringOrderIdNumber(divisor);
        }
        return result;
    }



}
