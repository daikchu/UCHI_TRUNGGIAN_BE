package com.vn.osp.notarialservices.identityAuthentication.dto;

import com.vn.osp.notarialservices.utils.Crypter;
import com.vn.osp.notarialservices.utils.config.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;

public class Test {

    private static String createAccessToken(String username, String password) {
        // Tạo access token
        Date expirationDate = new Date(System.currentTimeMillis() + 3600000); // Thời hạn 1 giờ
        String secretKey = "uchi_osp"; // Key bí mật để ký và xác minh token
        return Jwts.builder()
                .setSubject(username + ":" + password)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private static Claims decodeAccessToken(String accessToken, String secretKey) {
        // Giải mã access token
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(accessToken)
                .getBody();
    }

    public static void main(String[] args) {
//        String md5Hash = (Crypter.encodeToMD5("1123355589" + Constants.HEADER.USERNAME));
//        System.out.println(md5Hash);
        // Thông tin xác thực Basic Auth
//        String username = "your_username";
//        String password = "your_password";

        // Tạo access token ngẫu nhiên
//        String accessToken = createAccessToken(username, password);
//
//        // Tạo access token ngẫu nhiên
////        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Key bí mật để ký và xác minh token
////        String accessToken = Jwts.builder()
////                .setSubject(username)
////                .signWith(key)
////                .compact();
//
//        // In ra access token
//        System.out.println("Access Token: " + accessToken);
//
//
//        // Giải mã access token và trích xuất thông tin
//        Claims claims = decodeAccessToken(accessToken, "uchi_osp");
//
//        // Lấy thông tin từ token
//        String usernameInfo = claims.getSubject();
//        System.out.println(usernameInfo);
//        String expirationInfo = claims.getExpiration().toString();
//        System.out.println(expirationInfo);

        System.out.println(Crypter.encrypt("uchi_osp"));
        System.out.println(Crypter.encrypt("Uchi@123"));

        System.out.println(Crypter.decrypt("G+y7Ltc/oRkXQ4UvR8qbHg=="));
        System.out.println(Crypter.decrypt("wJ2/cuf4CJzSxKw//5hxgw=="));
}
}
