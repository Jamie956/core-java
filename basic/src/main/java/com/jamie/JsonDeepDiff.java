package com.jamie;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Iterator;

/**
 * Json 深度比较
 */
public class JsonDeepDiff {

    public static void compareJsonObject(JSONObject json1, JSONObject json2) {
        for (String key : json1.keySet()) {
            compareObject(json1.get(key), json2.get(key), key);
        }
    }

    public static void compareObject(Object json1, Object json2, String key) {
        if (json1 instanceof JSONObject) {
            compareJsonObject((JSONObject) json1, (JSONObject) json2);
        } else if (json1 instanceof JSONArray) {
            compareJsonArray((JSONArray) json1, (JSONArray) json2, key);
        } else if (json1 instanceof String) {
            try {
                compareJsonStr(json1.toString(), json2.toString(), key);
            } catch (Exception e) {
                System.out.println("转换发生异常 key:" + key);
                e.printStackTrace();
            }
        } else {
            compareJsonStr(json1.toString(), json2.toString(), key);
        }
    }

    public static void compareJsonStr(String str1, String str2, String key) {
        if (!str1.equals(str2)) {
            System.err.println(String.format("字符串比较不一致 json1 %s=%s, json2 %s=%s", key, str1, key, str2));
        } else {
            System.out.println(String.format("字符串比较一致 json1 %s=%s, json2 %s=%s", key, str1, key, str2));
        }
    }

    public static void compareJsonArray(JSONArray json1, JSONArray json2, String key) {
        if (json1 != null && json2 != null) {
            //数组逐个元素对象顺序比较，打乱顺序会导致不一致判断
            Iterator<Object> i1 = json1.iterator();
            Iterator<Object> i2 = json2.iterator();
            while (i1.hasNext()) {
                compareObject(i1.next(), i2.next(), key);
            }
        } else {
            if (json1 == null && json2 == null) {
                System.err.println(String.format("两个json key=%s 的值都是null", key));
            } else if (json1 == null) {
                System.err.println(String.format("json1 key=%s 的值是null", key));
            } else {
                System.err.println(String.format("json2 key=%s 的值是null", key));
            }
        }
    }

    /**
     * 其中一个json 不包含某个key
     */
    @Test
    public void case1() {
        String st1 = "{\"username\":\"tom\",\"age\":18,\"address\":[{\"province\":\"上海市\"},{\"city\":\"上海市\"},{\"disrtict\":\"静安区\"}]}";
        String st2 = "{username:\"tom\",age:18}";
        JSONObject jsonObject1 = JSONObject.parseObject(st1);
        JSONObject jsonObject2 = JSONObject.parseObject(st2);
        compareJsonObject(jsonObject1, jsonObject2);
    }

    /**
     * address 数组元素乱序
     */
    @Test
    public void case2() {
        String st1 = "{\"username\":\"tom\",\"age\":18,\"address\":[{\"province\":\"上海市\"},{\"city\":\"上海市\"},{\"disrtict\":\"静安区\"}]}";
        String st2 = "{\"username\":\"tom\",\"age\":18,\"address\":[{\"province\":\"上海市\"},{\"city\":\"上海市\"},{\"disrtict\":\"静安区\"}]}";
        JSONObject jsonObject1 = JSONObject.parseObject(st1);
        JSONObject jsonObject2 = JSONObject.parseObject(st2);
        compareJsonObject(jsonObject1, jsonObject2);
    }

}