package com.cat.json;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

public class JsonSimpleDiff {
    /**
     * 简单比较两个json key和value 是否有变更过
     * 1.比较两个json keyset 关系
     * 2.比较两个json value 是否一致
     * <p>
     * 交集：A & B，即A与B ( x x　( 😊 😊 ) x　x　)
     * 并集：A | B， 即A或B ( 😊 😊 ( 😊 😊 ) 😊 😊 )
     * 差集：A - B， 即A减B ( 😊 😊 ( x　x )　x　x　）
     * 补集：A ^ B，即A异B ( 😊 😊 ( x x )　😊 😊　）
     */
    public boolean isJsonSame(JSONObject j1, JSONObject j2) {
        if (j1 == null || j2 == null) {
            return false;
        }

        Set<String> keySet1 = j1.keySet();
        Set<String> keySet2 = j2.keySet();

        //长度没有改变，判断key是否修改过
        if (keySet1.size() == keySet2.size()) {
            for (String key : keySet1) {
                if (!keySet2.contains(key)) {
                    System.out.println("keyset长度一致，key发生变更");
                    return false;
                }
            }
        } else {
            //j2 - j1 即j2新增的key
            Set<String> j2NewKey = keySet2.stream().filter(e -> !keySet1.contains(e)).collect(Collectors.toSet());
            System.out.println(String.format("j2新增的key: %s", j2NewKey));
            if (!j2NewKey.isEmpty()) {
                return false;
            }

            //j1 - j2 即j2减少的key
            Set<String> j2SubKey = keySet1.stream().filter(e -> !keySet2.contains(e)).collect(Collectors.toSet());
            System.out.println(String.format("j2减少的key: %s", j2SubKey));
            if (!j2SubKey.isEmpty()) {
                return false;
            }
        }
        //比较同一个key 的value 值
        for (String k1 : j1.keySet()) {
            String v1 = j1.getString(k1);
            String v2 = j2.getString(k1);
            if (!v1.equals(v2)) {
                System.out.println(String.format("两个json key=%s 的 value 不一致 %s - %s", k1, v1, v2));
                return false;
            }
        }
        return true;
    }

    /**
     * 两个json 的key 一样
     */
    @Test
    public void case1() {
        String s1 = "{username:\"tom\",age:18}";
        String s2 = "{username:\"tom\",age:18}";
        JSONObject j1 = JSONObject.parseObject(s1);
        JSONObject j2 = JSONObject.parseObject(s2);
        System.out.println(isJsonSame(j1, j2));
    }

    /**
     * 两个json 的key 一样
     */
    @Test
    public void case10() {
        String s1 = "{username:\"tom\",age:18}";
        String s2 = "{username:\"tom\",age1:18}";
        JSONObject j1 = JSONObject.parseObject(s1);
        JSONObject j2 = JSONObject.parseObject(s2);
        System.out.println(isJsonSame(j1, j2));
    }


    /**
     * 两个json1 多了个key
     */
    @Test
    public void case2() {
        String s1 = "{username:\"tom\",age:18}";
        String s2 = "{username:\"tom\"}";
        JSONObject j1 = JSONObject.parseObject(s1);
        JSONObject j2 = JSONObject.parseObject(s2);
        System.out.println(isJsonSame(j1, j2));
    }

    /**
     * 两个json2 多了个key
     */
    @Test
    public void case3() {
        String s1 = "{username:\"tom\"}";
        String s2 = "{username:\"tom\",age:18}";
        JSONObject j1 = JSONObject.parseObject(s1);
        JSONObject j2 = JSONObject.parseObject(s2);
        System.out.println(isJsonSame(j1, j2));
    }

    /**
     * 两个json 某个key 的value 不一样
     */
    @Test
    public void case4() {
        String s1 = "{username:\"tom\",age:18}";
        String s2 = "{username:\"tim\",age:18}";
        JSONObject j1 = JSONObject.parseObject(s1);
        JSONObject j2 = JSONObject.parseObject(s2);
        System.out.println(isJsonSame(j1, j2));
    }

}
