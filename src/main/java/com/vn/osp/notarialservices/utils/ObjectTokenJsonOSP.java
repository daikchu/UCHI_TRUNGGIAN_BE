package com.vn.osp.notarialservices.utils;
import lombok.Data;

@Data
public class ObjectTokenJsonOSP {
    private String access_token;
    private String token_type;
    private long create_date;
    private long expires_in;
}
