package com.vn.osp.notarialservices.paymentTransaction.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.notarialservices.identityAuthentication.dto.ResultTokenGenerate;
import com.vn.osp.notarialservices.utils.AuthorizationUtil;
import com.vn.osp.notarialservices.utils.ObjectTokenJson;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
@Component
public class TokenServiceImpl implements TokenService{
    public ResponseEntity<Object> getTokenVietQR(String authorization, HttpServletRequest request){
        ObjectTokenJson objectTokenJson = new ObjectTokenJson();
        ResultTokenGenerate result = new ResultTokenGenerate();
        if(AuthorizationUtil.validateAccessToken(authorization)) {
            try {
                String user_pass_auth = Constants.HEADER_AUTH_BASIC_VIETQR.USERNAME + Constants.REGEX + Constants.HEADER_AUTH_BASIC_VIETQR.PASSWORD;
                String authorizationVietQR = Base64.getEncoder().encodeToString(user_pass_auth.getBytes());
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/json");
                headers.add("Authorization", "Basic " + authorizationVietQR);
                HttpEntity<String> entity = new HttpEntity<String>(headers);
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> string = restTemplate.exchange(Constants.URL_GET_TOKEN, HttpMethod.POST, entity, String.class);
                ObjectMapper mapper = new ObjectMapper();
                objectTokenJson = mapper.readValue(string.getBody(), ObjectTokenJson.class);
            } catch (Exception ex) {
                ex.printStackTrace();
                result.setTimestamp(new Date().getTime());
                result.setStatus(401);
                result.setError("Unauthorized");
                result.setMessage("");
                result.setPath(request.getRequestURI());
                return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(objectTokenJson, HttpStatus.OK);
        }else {
            result.setTimestamp(new Date().getTime());
            result.setStatus(401);
            result.setError("Unauthorized");
            result.setMessage("");
            result.setPath(request.getRequestURI());
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }
}
