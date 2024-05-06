package com.vn.osp.notarialservices.identityAuthentication.controller;

import com.google.gson.Gson;
import com.vn.osp.notarialservices.identityAuthentication.dto.InputGenerateVietQRCodeWithBank;

public class Test {
    public static void main(String[] args) {
        InputGenerateVietQRCodeWithBank inputCreateVietQRCode = new InputGenerateVietQRCodeWithBank(
                "1123355589",
                "1000",
                "test",
                "Pham Duc Hieu",
                "MB",
                "C",
                "23091204",
                "25f3e49-5766-47ad-acb4-d9340463eb2");
        String jsonInString = new Gson().toJson(inputCreateVietQRCode);
        System.out.println(jsonInString);
    }
}
