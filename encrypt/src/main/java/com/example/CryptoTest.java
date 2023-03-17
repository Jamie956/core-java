package com.example;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class CryptoTest {
    /**
     * 对称加密，加密和解密都是同一个密钥
     * DES : Data Encryption Standard，即数据加密标准，是一种使用密钥加密的块算法，1977年被美国联邦政府的国家标准局确定为联邦资料处理标准（FIPS），并授权在非密级政府通信中使用，随后该算法在国际上广泛流传开来。
     */
    @Test
    public void desTest() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String rawData = "tomcat";
        String key = "njkasdgh";
        // 算法
        String algorithm = "DES";
        String transformation = "DES";

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptBytes = cipher.doFinal(rawData.getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        String decryptStr = new String(decryptBytes, StandardCharsets.UTF_8);

        Assert.assertEquals(rawData, decryptStr);
    }

    /**
     * 对称加密，加密和解密都是同一个密钥
     * AES : Advanced Encryption Standard, 高级加密标准 .在密码学中又称Rijndael加密法，是美国联邦政府采用的一种区块加密标准。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。
     */
    @Test
    public void aesTest() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String rawData = "tomcat";
        String key = "NKvVyDwnIKSDRABpR7NO9w==";
        // 算法
        String algorithm = "AES";
        String transformation = "AES";

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptBytes = cipher.doFinal(rawData.getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        String decryptStr = new String(decryptBytes, StandardCharsets.UTF_8);

        Assert.assertEquals(rawData, decryptStr);
    }

    // AES-128-ECB加密
    @Test
    public void aes128ecbTest() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String rawData = "tomcat";
        String key = "jkl;POIU1234++==";
        // 算法
        String algorithm = "AES";
        // 算法/模式/补码方式
        String transformation = "AES/ECB/PKCS5Padding";

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptBytes = cipher.doFinal(rawData.getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        String decryptStr = new String(decryptBytes, StandardCharsets.UTF_8);

        Assert.assertEquals(rawData, decryptStr);
    }

    /**
     * AES-128-CBC 加密
     * 使用CBC模式，需要一个向量iv，可增加加密算法的强度
     *
     * @author ZJM
     * @date 2021/9/8 11:05
     */
    @Test
    public void aes128cbcTest() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 算法
        String algorithm = "AES";
        // 算法/模式/补码方式
        String transformation = "AES/CBC/PKCS5Padding";
        // 密钥
        String key = "xl9YTgGf6jUk1EZE9Wohcg==";
        // 向量
        String iv = "IZ0Cl5x7MZba3DdG";
        // 要加密的数据
        String data = "{data:[{'name':'hi','age':20},{'name':'zd','age':18}]}";

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        Cipher cipher = Cipher.getInstance(transformation);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encryptBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        String decryptStr = new String(decryptBytes, StandardCharsets.UTF_8);

        Assert.assertEquals(data, decryptStr);
    }

    // 生成AES DES 密钥
    @Test
    public void generateAesKeyTest() throws Exception {
        int length = 128;
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        //设置密钥长度
        keyGenerator.init(length);
        SecretKey key = keyGenerator.generateKey();
        byte[] bytes = key.getEncoded();
        String genKey = Base64.getEncoder().encodeToString(bytes);
        Assert.assertNotNull(genKey);
    }


}
