package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public class SerializeTest {
    public static class Object33 {
        // 自定义的json反序列类，to object date 时转成 object date
        @JsonDeserialize(using = CustomDateDeserializer.class)
        public Date eventDate;
    }
    @Test
    public void deserializeTest() throws IOException {
        Object33 o = new ObjectMapper().readerFor(Object33.class)
                .readValue("{\"eventDate\":\"20-12-2014\"}");
        Assert.assertEquals(1419004800000L, o.eventDate.getTime());
    }

    public static class Object05 {
        // 自定义的序列化类，to json 时转成 format date
        @JsonSerialize(using = CustomDateSerializer.class)
        public Date eventDate;
    }
    @Test
    public void serializeTest() throws JsonProcessingException {
        Object05 o = new Object05();
        o.eventDate = new Date(1681394691967L);
        String result = new ObjectMapper().writeValueAsString(o);
        Assert.assertEquals("{\"eventDate\":\"13-04-2023\"}", result);
    }
}
