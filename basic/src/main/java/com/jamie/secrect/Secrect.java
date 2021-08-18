package com.jamie.secrect;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 加密解密
 */
public class Secrect {
    /**
     * 对称加密，加密和解密都是同一个密钥
     * <p>
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

    /**
     * 数字摘要 md5
     */
    @Test
    public void md5Test() throws NoSuchAlgorithmException {
        // 原文
        String input = "aa";
        // 算法
        String algorithm = "MD5";
        // 获取数字摘要对象
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        // 获取消息数字摘要的字节数组
        byte[] digest = messageDigest.digest(input.getBytes());
        System.out.println(Base64.getEncoder().encodeToString(digest));

        //16进制
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            // 转成 16进制
            String s = Integer.toHexString(b & 0xff);
            //System.out.println(s);
            if (s.length() == 1) {
                // 如果生成的字符只有一个，前面补0
                s = "0" + s;
            }
            sb.append(s);
        }
        System.out.println(sb.toString());
    }

    /**
     * 非对称加密RSA
     */
    @Test
    public void rsaTest() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String input = "你好";
        // 加密算法
        String algorithm = "RSA";
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 生成公钥
        PublicKey publicKey = keyPair.getPublic();

        // 获取私钥字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 获取公钥字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded();

        // 对公私钥进行base64编码
        String privateKeyString = Base64.getEncoder().encodeToString(privateKeyEncoded);
        String publicKeyString = Base64.getEncoder().encodeToString(publicKeyEncoded);

        System.out.println("私钥：" + privateKeyString);
        System.out.println("公钥：" + publicKeyString);

        Cipher cipher = Cipher.getInstance(algorithm);
        //私钥加密
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(input.getBytes());
        System.out.println("私钥加密：" + Base64.getEncoder().encodeToString(bytes));

        // 公钥解密
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] bytes1 = cipher.doFinal(bytes);
        System.out.println("公钥解密：" + new String(bytes1));

    }

    /**
     * 生成密钥对并保存在本地文件中
     *
     * @param algorithm : 算法
     * @param pubPath   : 公钥保存路径
     * @param priPath   : 私钥保存路径
     * @throws Exception
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
        FileUtils.writeStringToFile(new File(pubPath), publicKeyString, Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(priPath), privateKeyString, Charset.forName("UTF-8"));

    }

    public static PrivateKey getPrivateKey(String priPath, String algorithm) throws Exception {
        // 将文件内容转为字符串
        String privateKeyString = FileUtils.readFileToString(new File(priPath), Charset.defaultCharset());
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范 进行Base64解码
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString));
        // 生成私钥
        return keyFactory.generatePrivate(spec);
    }


    public static PublicKey getPublicKey(String pulickPath, String algorithm) throws Exception {
        // 将文件内容转为字符串
        String publicKeyString = FileUtils.readFileToString(new File(pulickPath), Charset.defaultCharset());
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范 进行Base64解码
        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
        // 生成公钥
        return keyFactory.generatePublic(spec);
    }

}
