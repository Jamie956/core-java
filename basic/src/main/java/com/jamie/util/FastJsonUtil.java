package com.jamie.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jamie.entity.JsonUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Zjm
 * @Date: 2021/2/5 16:00
 */
public class FastJsonUtil {

    /**
     * 递归遍历json 全部节点
     */
    public static void recursion(Object object) {
        if (object instanceof JSONObject) {
            JSONObject json = (JSONObject) object;
            for (String key : json.keySet()) {
                Object value = json.get(key);
                recursion(value);
            }
        } else if (object instanceof JSONArray) {
            JSONArray array = (JSONArray) object;
            for (Object element : array) {
                recursion(element);
            }
        }
    }

    @Test
    public void testRecursion() {
        String jsonString = "{\"TITLE\":\"Json Title\",\"FORM\":{\"USERNAME\":\"Rick and Morty\"},\"ARRAY\":[{\"FIRST\":\"Rick\"},{\"LAST\":\"Morty\"}]}";
        JSONObject json = JSON.parseObject(jsonString);
        recursion(json);
    }

    /**
     * json double 排序
     */
    @Test
    public void doubleSort() {
        JSONObject j1 = new JSONObject();
        j1.put("value", 0.01);
        JSONObject j2 = new JSONObject();
        j2.put("value", 7.11);
        JSONObject j3 = new JSONObject();
        j3.put("value", 3.01);
        List<JSONObject> list = Arrays.asList(j1, j2, j3);
        List<JSONObject> value = list.stream().sorted(Comparator.comparingDouble((JSONObject e) -> e.getDoubleValue("value"))).collect(Collectors.toList());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class User implements Serializable {
        public Integer age;
        public String name;
    }

    public static void main(String[] args) {
        JsonUser user = new JsonUser("tom", "100");
        //java object -> json string
        String userStr = JSON.toJSONString(user);

        //json string -> json
        JSONObject userJson = JSON.parseObject(userStr);

        //json string -> java object
        JsonUser userObj = JSON.parseObject(userStr, JsonUser.class);

        //map -> json
        Map<String,Object> map = new HashMap<>();
        map.put("age", 24);
        map.put("name", "11111");
        JSONObject json = new JSONObject(map);

        //json -> string
        String jsonString = json.toJSONString();

        //json -> java object
        User user1 = json.toJavaObject(User.class);
        User user2 = JSON.toJavaObject(json, User.class);

        //string -> array
        JSONArray jsonArray = JSON.parseArray("[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]");
        //array -> string
        String jsonString123 = JSON.toJSONString(jsonArray);

    }
}
