package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public class SerializeTest {
    public static class Object33 {
        @JsonDeserialize(using = CustomDateDeserializer.class)
        public Date eventDate;
    }
    @Test
    public void customDeserializeTest() throws IOException {
        Object33 o = new ObjectMapper().readerFor(Object33.class)
                .readValue("{\"eventDate\":\"20-12-2014 02:30:00\"}");
        Assert.assertEquals(1419013800000L, o.eventDate.getTime());
    }
}
