package com.example;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Test {
    @Test
    public void encodeAndDecode() {
        String data = "tome";
        String encodeStr = Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
        byte[] decodeBytes = Base64.getDecoder().decode(encodeStr);
        String decodeStr = new String(decodeBytes, StandardCharsets.UTF_8);

        Assert.assertEquals("tome", decodeStr);
    }
}
