package com.example;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SecurityTest {
    /**
     * 生成base64 md5
     */
    @Test
    public void generateMd5Base64() throws NoSuchAlgorithmException {
        String input = "ssdfdfd";
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(input.getBytes());
        String base64md5 = Base64.getEncoder().encodeToString(digest);
        Assert.assertNotNull(base64md5);
    }

    /**
     * 生成 数字摘要 md5
     */
    @Test
    public void generateMd5() throws NoSuchAlgorithmException {
        String input = "2333";

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
        Assert.assertNotNull(sb.toString());
    }


    /**
     * 非对称加密RSA
     * 私钥加密，公钥解密
     */
    @Test
    public void rsaTest() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String input = "2121212sdfssa";

        KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(publicKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(privateSpec);
        PublicKey publicKey = keyFactory.generatePublic(publicSpec);

        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptBytes = cipher.doFinal(input.getBytes());

        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        String decryptStr = new String(decryptBytes, StandardCharsets.UTF_8);

        Assert.assertEquals(input, decryptStr);
    }

}
