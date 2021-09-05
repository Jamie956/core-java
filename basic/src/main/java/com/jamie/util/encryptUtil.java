package com.jamie.util;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 加密解密
 */
public class encryptUtil {
    /**
     * 对称加密，加密和解密都是同一个密钥
     * DES : Data Encryption Standard，即数据加密标准，是一种使用密钥加密的块算法，1977年被美国联邦政府的国家标准局确定为联邦资料处理标准（FIPS），并授权在非密级政府通信中使用，随后该算法在国际上广泛流传开来。
     * AES : Advanced Encryption Standard, 高级加密标准 .在密码学中又称Rijndael加密法，是美国联邦政府采用的一种区块加密标准。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。
     */
    @Test
    public void desTest() throws Exception {
        String input = "你好";
        String key = "12345678";
        String transformation = "DES";
        String algorithm = "DES";

        String encryptDes = encryptDES(input, key, transformation, algorithm);
        System.out.println("加密:" + encryptDes);

        String s = decryptDES(encryptDes, key, transformation, algorithm);
        System.out.println("解密:" + s);
    }

    /**
     * 对称加密，加密和解密都是同一个密钥
     * DES: Data Encryption Standard
     */
    private static String encryptDES(String input, String key, String transformation, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, sks);
        byte[] bytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * DES解密
     */
    private static String decryptDES(String input, String key, String transformation, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.DECRYPT_MODE, sks);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(input));
        return new String(bytes);
    }

    @Test
    public void md5Test() throws NoSuchAlgorithmException {
        System.out.println(generateMd5("aaa"));
    }

    /**
     * 生成 数字摘要 md5
     */
    public static String generateMd5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(input.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            // 转成 16进制
            String s = Integer.toHexString(b & 0xff);
            if (s.length() == 1) {
                // 如果生成的字符只有一个，前面补0
                s = "0" + s;
            }
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 生成base64 md5
     */
    public static String generateMd5Base64(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(input.getBytes());
        return Base64.getEncoder().encodeToString(digest);
    }

    /**
     * 非对称加密RSA
     */
    @Test
    public void keypaiPTest() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        Map<String, String> keyMap = generateBase64KeyPair();

        String encryptMessage = encryptByPrivateKey(keyMap.get("private"), "你好");
        String decodeMessage = decryptByPublicKey(keyMap.get("public"), encryptMessage);
    }

    /**
     * 私钥加密
     */
    public static String encryptByPrivateKey(String base64PrivateKey, String input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 构建密钥规范 进行Base64解码
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey));
        PrivateKey privateKey = keyFactory.generatePrivate(spec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 公钥解密
     */
    public static String decryptByPublicKey(String base64PublicKey, String input) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 构建密钥规范 进行Base64解码
        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey));
        PublicKey publicKey = keyFactory.generatePublic(spec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decode = Base64.getDecoder().decode(input);
        byte[] bytes = cipher.doFinal(decode);
        return new String(bytes);
    }

    /**
     * 生成 base64 公钥和私钥
     */
    public static Map<String, String> generateBase64KeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] privateKeyEncoded = privateKey.getEncoded();
        String base64PrivateKey = Base64.getEncoder().encodeToString(privateKeyEncoded);

        // 生成公钥
        PublicKey publicKey = keyPair.getPublic();
        byte[] publicKeyEncoded = publicKey.getEncoded();
        String base64PublicKey =  Base64.getEncoder().encodeToString(publicKeyEncoded);

        Map<String, String> map = new HashMap<>();
        map.put("public", base64PublicKey);
        map.put("private", base64PrivateKey);
        return map;
    }

    /**
     * 生成公钥密钥，保存到文件
     */
    private static void generateKeyToFile(String algorithm, String pubPath, String priPath) throws Exception {
        // 获取密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 获取密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 获取公钥
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥
        PrivateKey privateKey = keyPair.getPrivate();

        // 进行Base64编码
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        // 保存文件
        FileUtils.writeStringToFile(new File(pubPath), publicKeyString, StandardCharsets.UTF_8);
        FileUtils.writeStringToFile(new File(priPath), privateKeyString, StandardCharsets.UTF_8);

    }

    /**
     * 读取文件私钥
     */
    public static PrivateKey getPrivateKey(String path, String algorithm) throws Exception {
        String base64PrivateKey = FileUtils.readFileToString(new File(path), Charset.defaultCharset());
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范 进行Base64解码
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey));
        // 生成私钥
        return keyFactory.generatePrivate(spec);
    }

    /**
     * 读取文件公钥
     */
    public static PublicKey getPublicKey(String path, String algorithm) throws Exception {
        String publicKeyString = FileUtils.readFileToString(new File(path), Charset.defaultCharset());
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范 进行Base64解码
        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
        // 生成公钥
        return keyFactory.generatePublic(spec);
    }

}
