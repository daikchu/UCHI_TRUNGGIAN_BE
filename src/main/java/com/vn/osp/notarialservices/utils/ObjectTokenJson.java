package com.vn.osp.notarialservices.utils;
import lombok.Data;

@Data
public class ObjectTokenJson {
    private String access_token;
    private String token_type;
    private String expires_in;
}
