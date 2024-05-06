package com.vn.osp.notarialservices.identityAuthentication.dto;
import java.util.HashMap;
import java.util.Map;

public class OrderCodeGenerator {
    public static <K,V> K getKeyByValue(Map<K,V> map, V valueToFind) {
        for (Map.Entry<K,V> entry : map.entrySet()) {
            if (valueToFind.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null; // Trả về null nếu không tìm thấy key tương ứng với value
    }

    public static void main(String[] args) {

        int dividend = 999999*26*26; // Số bị chia
        System.out.println(dividend);
        int divisor = 999999;  // Số chia

        int gt_sau_chia = dividend / divisor; // Giá trị sau khi chia
        int phan_du = dividend % divisor; // Phần dư

        System.out.println("Kết quả của phép chia: " + gt_sau_chia);
        System.out.println("Phần dư: " + phan_du);

        Map<String, Integer> stringMap = new HashMap<>();

        Integer indexChar = 0;
        for (char char1 = 'A'; char1 <= 'Z'; char1++) {
            for (char char2 = 'A'; char2 <= 'Z'; char2++) {
                stringMap.put(""+char1 + char2+"", indexChar);
                indexChar++;
            }
        }

        for (Map.Entry<String, Integer> entry : stringMap.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
        if(phan_du == 0){
            String key = getKeyByValue(stringMap, gt_sau_chia-1);
            System.out.println("Mã đơn hàng: " + "DH" + key + divisor);
        }else {
            String key = getKeyByValue(stringMap, gt_sau_chia);
            System.out.println("Mã đơn hàng: " + "DH" + key + phan_du);
        }
    }
}
