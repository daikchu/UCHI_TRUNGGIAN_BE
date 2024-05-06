package com.vn.osp.notarialservices.utils;


import com.vn.osp.notarialservices.identityAuthentication.dto.ResultTokenGenerate;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

public class AuthorizationUtil {
    public static boolean validateAccessToken(String authorization){
        String access_token = parseBearerAuthHeader(authorization);
        String secretKey = Constants.SECRET_KEY;
        boolean isSuccess = TokenGenerateUtil.decodeAccessToken(access_token, secretKey);
      return isSuccess;
    }

    public static String parseBearerAuthHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String access_token = authorizationHeader.substring(6); // access_token
            return access_token;
        }
        return null;
    }
}
