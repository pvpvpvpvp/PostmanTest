package com.example.postmantestapi.testEncryption;

import com.example.postmantestapi.filter.AESCrypto;

public class TestEncryption {

    public static void main(String[] args) {
        try {
            String secretKey = "1234567890123456"; // 16바이트 길이
            String plainText = "LnVpOmF0Gz+2cPYKBKPIvDNdd7ux0xebmb63Ov7X5DE0GDsc/wRe1IUCz2l6u6XBfZVvBkMBWWo1zyfT1MiDFWRbZFRaRti9qXrzX8sjBBM="; // 테스트할 문자열

            // 암호화
            String String = AESCrypto.decrypt(plainText, secretKey);
            System.out.println("String: " + String);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
