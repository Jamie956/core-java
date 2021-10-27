package com.jamie.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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

    @Data
    @AllArgsConstructor
    static class JsonUser implements Serializable {
        private static final long serialVersionUID = -4537716904357183030L;

        //定义转成json 对应字段的名字
        @JSONField(name = "jsonFieldCustomName")
        private String name;
        private String age;
        //字段不转成JSON
        @JSONField(serialize = false)
        private List<String> tags;

        public JsonUser(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }

    /**
     * JSONField name: 对象 转json 使用别名
     * JSONField serialize = false：字段不转成JSON
     */
    @Test
    public void jsonFileNameConvert() {
        List<String> tags = Arrays.asList("a", "b");
        JsonUser user = new JsonUser("tom", "12", tags);
        String s = JSON.toJSONString(user);
        System.out.println(s);
    }

    @Data
    @AllArgsConstructor
    public static class User {
        private String name;
        private String age;
    }

    /**
     * JSON.parseXXX: string to ?
     * JSON.toXXX: json to ?
     */
    public static void main(String[] args) {
        //java object -> json string
        String userStr = JSON.toJSONString(new User("tom", "100"));

        //json string -> json
        JSONObject userJson = JSON.parseObject("{\"age\":\"100\",\"name\":\"tom\"}");

        //json string -> java object
        User userObj = JSON.parseObject("{\"age\":\"100\",\"name\":\"tom\"}", User.class);

        //map -> json
        Map<String,Object> map = new HashMap<>();
        map.put("age", "24");
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

    @Data
    static class DateTest {
        private String author;
        @JSONField(format = "yyyy-MM-dd")
        private Date pubTime;
    }

    /**
     * 转换日期格式(yyyy-MM-d) json
     */
    @Test
    public void jsonStr2ObjectDateField() {
        String jsonStr = "{ \"author\": \"环京津网\", \"pub_time\": \"2020-11-5\" }";
        DateTest dateTest = JSON.parseObject(jsonStr, DateTest.class);
    }

}
