package com.vn.osp.notarialservices.paymentTransaction.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    ResponseEntity<Object> getTokenVietQR(String authorization, HttpServletRequest request);
}
