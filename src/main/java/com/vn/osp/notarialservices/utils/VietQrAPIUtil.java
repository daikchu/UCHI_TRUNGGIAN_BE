package com.vn.osp.notarialservices.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.notarialservices.identityAuthentication.dto.*;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class VietQrAPIUtil {
    private static final Logger LOGGER = Logger.getLogger(VietQrAPIUtil.class);

    public static ResultCode callAPIGenerateVietQRCode(String actionURL, Map<String, String> headers, String data) {
        HttpURLConnection conn =null;
        ResultCode result =  new ResultCode();
        ResultErrorCode errorCode = new ResultErrorCode();
        OutputGenerateVietQRCode outputGenerateVietQRCode = new OutputGenerateVietQRCode();
        try {
            URL url = new URL(actionURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", headers.get("Authorization"));
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            String input = data;
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes("UTF-8"));
            os.flush();

            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream()), "UTF-8"));
                String output;
                ObjectMapper mapper = new ObjectMapper();

                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                while ((output = br.readLine()) != null) {
                    outputGenerateVietQRCode = mapper.readValue(output, OutputGenerateVietQRCode.class);
                    result.setStatus(String.valueOf(HttpStatus.OK));
                    result.setMessage("OK");
                    result.setData(outputGenerateVietQRCode);
                }
            }
            else if (conn.getResponseCode() != 200) {
                if(conn.getResponseCode() == 400){
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    String output;
                    ObjectMapper mapper = new ObjectMapper();

                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    while ((output = br.readLine()) != null) {
                        errorCode = mapper.readValue(output, ResultErrorCode.class);
                        result.setStatus(errorCode.getStatus());
                        result.setMessage(errorCode.getMessage());
                        result.setData(null);
                    }
                }else { // case xử lý lỗi 403 Forbidden
                    result.setStatus("403");
                    result.setMessage("");
                    result.setData(null);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Have an error in method VietQrAPIUtil.callAPIGenerateVietQRCode:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return result;
    }

    public static ResultOutputCheckOrder callAPICheckOrder(String actionURL, Map<String, String> headers, String data) {
        HttpURLConnection conn =null;
        ResultOutputCheckOrder result = new ResultOutputCheckOrder();
        ResultErrorCode errorCode = new ResultErrorCode();
        OutputCheckOrder[] outputCheckOrder;
        try {
            URL url = new URL(actionURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", headers.get("Authorization"));
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            String input = data;
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes("UTF-8"));
            os.flush();

            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream()), "UTF-8"));
                String output;
                ObjectMapper mapper = new ObjectMapper();

                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                while ((output = br.readLine()) != null) {
//                        JSONArray jsonArray = new JSONArray(output);
//                        if (jsonArray != null) {
//                            int len = jsonArray.length();
//                            for (int i = 0; i < len; i++) {
//                                list.add(mapper.readValue(jsonArray.get(i).toString(), TransactionProperty.class));
//
//                            }
//                        }
                    outputCheckOrder = mapper.readValue(output, OutputCheckOrder[].class);
                    result.setStatus(String.valueOf(HttpStatus.OK));
                    result.setMessage("OK");
                    result.setData(outputCheckOrder);
                }
            }
            else if (conn.getResponseCode() != 200) {
                if(conn.getResponseCode() == 400){
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    String output;
                    ObjectMapper mapper = new ObjectMapper();

                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    while ((output = br.readLine()) != null) {
                        errorCode = mapper.readValue(output, ResultErrorCode.class);
                        result.setStatus(errorCode.getStatus());
                        result.setMessage(errorCode.getMessage());
                        result.setData(null);
                    }
                }else { // case xử lý lỗi 403 Forbidden
                    result.setStatus("403");
                    result.setMessage("");
                    result.setData(null);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Have an error in method VietQrAPIUtil.callAPICheckOrder:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return result;
    }
}
