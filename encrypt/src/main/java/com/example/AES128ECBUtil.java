package com.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AES-128-ECB加密
 */
public class AES128ECBUtil {
    /**
     * 算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 算法/模式/补码方式
     */
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public static String encrypt(String rawData, String secretKey) throws Exception {
        byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec spec = new SecretKeySpec(secretKeyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, spec);
        byte[] encrypted = cipher.doFinal(rawData.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptData, String secretKey) throws Exception {
        byte[] raw = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec spec = new SecretKeySpec(raw, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, spec);
        byte[] decryptData = cipher.doFinal(Base64.getDecoder().decode(encryptData));
        return new String(decryptData, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        String cKey = "jkl;POIU1234++==";
        String rawData = "www.gowhere.so";
        // 加密
        String encryptData = encrypt(rawData, cKey);
        System.out.println("加密后的字串是：" + encryptData);
        // 解密
        String decryptData = decrypt(encryptData, cKey);
        System.out.println("解密后的字串是：" + decryptData);
    }
}
