package com.jamie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @Author: Zjm
 * @Date: 2021/2/5 16:00
 */
public class Algo {

    /**
     * 递归遍历json 全部节点
     */
    public static void jsonRecursion(Object object) {
        if (object instanceof JSONObject) {
            JSONObject json = (JSONObject) object;
            for (String key : json.keySet()) {
                Object value = json.get(key);
                jsonRecursion(value);
            }
        } else if (object instanceof JSONArray) {
            JSONArray array = (JSONArray) object;
            for (Object element : array) {
                jsonRecursion(element);
            }
        }
    }

    @Test
    public void testRecursion() {
        String jsonString = "{\"TITLE\":\"Json Title\",\"FORM\":{\"USERNAME\":\"Rick and Morty\"},\"ARRAY\":[{\"FIRST\":\"Rick\"},{\"LAST\":\"Morty\"}]}";
        JSONObject json = JSON.parseObject(jsonString);
        jsonRecursion(json);
    }
}
