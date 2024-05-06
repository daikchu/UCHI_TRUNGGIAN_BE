package com.vn.osp.notarialservices.utils;
import com.vn.osp.notarialservices.identityAuthentication.dto.ResultTokenGenerate;
import com.vn.osp.notarialservices.utils.config.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class TokenGenerateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenGenerateUtil.class);
    public static ObjectTokenJsonOSP createAccessToken(String username, String password) {
        ObjectTokenJsonOSP result = new ObjectTokenJsonOSP();
        // Tạo access token
        long customCreateDate = System.currentTimeMillis();
        long expirationDate = Constants.EXPIRES_IN; // Thời hạn 5 phút
        String secretKey = Constants.SECRET_KEY; // Key bí mật để ký và xác minh token
        String access_token = Jwts.builder()
                .setSubject(username + ":" + password)
                .setIssuedAt(new Date(customCreateDate))
                .setExpiration(new Date(customCreateDate + expirationDate))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        result.setAccess_token(access_token);
        result.setToken_type("Bearer");
        result.setCreate_date(customCreateDate);
        result.setExpires_in(customCreateDate + expirationDate);
        return result;
    }
    public static boolean decodeAccessToken(String accessToken, String secretKey) {
        try{
            // Giải mã access token
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(accessToken)
                    .getBody();

            // Lây thông tin user/pass xác thực
            String subject = claims.getSubject();
            String[] decodedString = subject.split(":");
            // lấy thông tin thời gian hết hạn token
            long expirationDate = claims.getExpiration().getTime();
            if(!decodedString[0].equals(Constants.HEADER_AUTH_BASIC.USERNAME) || !decodedString[1].equals(Constants.HEADER_AUTH_BASIC.PASSWORD)){
                LOGGER.error("user hoặc pass authen API không chính xác: " + decodedString[0] + ":" + decodedString[1]);
                return false;
            }
            if(new Date().getTime() > expirationDate){
                LOGGER.error("access token đã hết hạn truy cập API: " + accessToken);
                return false;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            LOGGER.error("Có lỗi xảy ra khi giải mã token: "+ ex.getMessage());
            return false;
        }
        return true;
    }
}
