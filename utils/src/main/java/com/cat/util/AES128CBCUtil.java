package com.cat.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AES-128-CBC 加密
 * 使用CBC模式，需要一个向量iv，可增加加密算法的强度
 * @author ZJM
 * @date 2021/9/8 11:05
 */
public class AES128CBCUtil {
    /**
     * 算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 算法/模式/补码方式
     */
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    /**
     * 向量长度
     */
    private static final int IV_LENGTH = 16;

    /**
     * 加密
     *
     * @param rawData      要加密的数据
     * @param key 密钥
     * @param iv 向量
     * @return 加密后的数据
     */
    public static String encrypt(String rawData, String key, String iv) throws Exception {
        if (iv.length() != IV_LENGTH) {
            throw new RuntimeException("iv 长度不是16字节（128位）");
        }
        SecretKeySpec keySpec = new SecretKeySpec(toUtf8Byte(key), ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(toUtf8Byte(iv));
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encryptedData = cipher.doFinal(toUtf8Byte(rawData));
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * 解密
     *
     * @param encryptData 加密后的数据
     * @param key     密钥
     * @param iv 向量
     * @return 解密数据
     */
    public static String decrypt(String encryptData, String key, String iv) throws Exception {
        if (iv.length() != IV_LENGTH) {
            throw new RuntimeException("iv 长度不是16字节（128位）");
        }
        SecretKeySpec keySpec = new SecretKeySpec(toUtf8Byte(key), ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(toUtf8Byte(iv));
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptData));
        return new String(original, StandardCharsets.UTF_8);
    }

    public static byte[] toUtf8Byte(String s) {
        return s.getBytes(StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        //密钥
        String secret = "xl9YTgGf6jUk1EZE9Wohcg==";
        //向量
        String iv = "IZ0Cl5x7MZba3DdG";

        String data = "{data:[{'name':'hi','age':20},{'name':'zd','age':18}]}";
        // 加密
        String encryptedData = encrypt(data, secret, iv);
        System.out.println("加密后的字串是：" + encryptedData);
        // 解密
        String decryptedData = decrypt(encryptedData, secret, iv);
        System.out.println("解密后的字串是：" + decryptedData);
    }
}

