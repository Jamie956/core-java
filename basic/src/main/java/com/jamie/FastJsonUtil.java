package com.jamie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jamie.entity.JsonUser;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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

    public static void main(String[] args) {
        JsonUser user = new JsonUser("tom", "100");
        //java object -> string
        String userStr = JSON.toJSONString(user);
        //string -> json
        JSONObject userJson = JSON.parseObject(userStr);
        //string -> java object
        JsonUser userObj = JSON.parseObject(userStr, JsonUser.class);
        System.out.println();
    }
}
