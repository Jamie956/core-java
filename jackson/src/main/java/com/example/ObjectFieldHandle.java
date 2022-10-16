package com.example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

public class ObjectFieldHandle {

    public static class Object01 {
        public String name;
        private Map<String, String> properties;

        //flat
        @JsonAnyGetter
        public Map<String, String> getProperties() {
            return properties;
        }

        public Object01(String name, Map<String, String> properties) {
            super();
            this.name = name;
            this.properties = properties;
        }

        public Object01() {
            super();
        }
    }

    @JsonPropertyOrder({"name", "id"})
    public static class Object02 {
        public int id;
        public String name;

        public Object02(int id, String name) {
            super();
            this.id = id;
            this.name = name;
        }
    }

    public static class Object03 {
        @JsonRawValue
        public String json;

        public Object03(String json) {
            super();
            this.json = json;
        }
    }

    @JsonRootName(value = "rootName")
    public static class Object04 {
        public int id;

        public Object04(int id) {
            this.id = id;
        }
    }

    public static class Object05 {
        @JsonSerialize(using = CustomDateSerializer.class)
        public Date eventDate;

        public Object05(Date eventDate) {
            super();
            this.eventDate = eventDate;
        }
    }

    @Test
    public void flatObjectMapField() throws JsonProcessingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        Object01 o = new Object01("a", map);

        String result = new ObjectMapper().writeValueAsString(o);
        System.out.println(result);
    }

    @Test
    public void fieldOrder() throws JsonProcessingException {
        Object02 bean = new Object02(1, "a");
        String result = new ObjectMapper().writeValueAsString(bean);
        System.out.println(result);
    }

    @Test
    public void rawJsonField() throws JsonProcessingException {
        Object03 o = new Object03("{\"attr\":false}");
        String result = new ObjectMapper().writeValueAsString(o);
        System.out.println(result);
    }

    @Test
    public void object2StrWithRootName() throws JsonProcessingException {
        String ret = new ObjectMapper().enable(SerializationFeature.WRAP_ROOT_VALUE).writeValueAsString(new Object04(1));
        System.out.println(ret);
    }

    @Test
    public void fieldCustomSerializer() throws JsonProcessingException {
        String result = new ObjectMapper().writeValueAsString(new Object05(new Date()));
        System.out.println(result);
    }

}
