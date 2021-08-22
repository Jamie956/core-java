package com.jamie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jamie.entity.JsonUser;
import org.junit.Test;

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
