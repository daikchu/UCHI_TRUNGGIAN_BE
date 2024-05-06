package com.vn.osp.notarialservices.utils;

import com.vn.osp.notarialservices.utils.config.Constants;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Crypter {
    private static byte[] sharedvector = {
            0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11
    };
    public static String encodeToMD5(String input) {
        try {
            // Tạo một đối tượng MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Chuyển chuỗi thành mảng bytes
            byte[] inputBytes = input.getBytes();
            // Mã hoá mảng bytes
            byte[] hashBytes = md.digest(inputBytes);
            // Chuyển mảng bytes mã hoá thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Xử lý ngoại lệ nếu có lỗi
            e.printStackTrace();
            return null;
        }
    }
//    public static String encrypt(String encryptText) {
//        try {
//            SecretKeySpec secretKeySpec = new SecretKeySpec("YourSecretKey1234".getBytes(), "AES");
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
//            byte[] encryptedBytes = cipher.doFinal(encryptText.getBytes());
//            return Base64.getEncoder().encodeToString(encryptedBytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public static String decrypt(String decryptText) {
//        try {
//            SecretKeySpec secretKeySpec = new SecretKeySpec("YourSecretKey1234".getBytes(), "AES");
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
//            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(decryptText));
//            return new String(decryptedBytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public static String[] parseBasicAuthHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            String base64Credentials = authorizationHeader.substring(6); // Remove "Basic " prefix
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String decodedString = new String(decodedBytes);
            return decodedString.split(":");
        }
        return null;
    }
    public static String encrypt(String text)
    {
        String encryptText = "";
        byte[] keyArray = new byte[24];
        byte[] temporaryKey;
        String key = Constants.SECRET_KEY;
        byte[] toEncryptArray = null;

        try
        {

            toEncryptArray =  text.getBytes("UTF-8");
            MessageDigest m = MessageDigest.getInstance("MD5");
            temporaryKey = m.digest(key.getBytes("UTF-8"));

            if(temporaryKey.length < 24) // DESede require 24 byte length key
            {
                int index = 0;
                for(int i=temporaryKey.length;i< 24;i++)
                {
                    keyArray[i] =  temporaryKey[index];
                }
            }

            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
            byte[] encrypted = c.doFinal(toEncryptArray);
            encryptText = org.apache.commons.codec.binary.Base64.encodeBase64String(encrypted);
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return encryptText;
    }

    public static String decrypt(String encryptText)
    {
        String text = "";
        byte[] keyArray = new byte[24];
        byte[] temporaryKey;
        String key = Constants.SECRET_KEY;
        byte[] toEncryptArray = null;

        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            temporaryKey = m.digest(key.getBytes("UTF-8"));

            if(temporaryKey.length < 24) // DESede require 24 byte length key
            {
                int index = 0;
                for(int i=temporaryKey.length;i< 24;i++)
                {
                    keyArray[i] =  temporaryKey[index];
                }
            }

            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
            byte[] decrypted = c.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(encryptText));

            text = new String(decrypted, "UTF-8");
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return text;

    }
}

