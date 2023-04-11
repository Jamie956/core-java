import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FastJsonApiTest {
    @Data
    static class AlternateNamesObject {
        // 字段别名，json key abstract/summary1 可以映射到本字段
        @JSONField(alternateNames = {"abstract", "summary1"})
        private String summary;
    }

    @Test
    public void alternateNamesMappingTest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("abstract", "aaa");
        String jsonString = jsonObject.toJSONString();
        Assert.assertEquals("{\"abstract\":\"aaa\"}", jsonString);

        // json key 映射对象别名并设置到对象字段
        AlternateNamesObject result = JSON.parseObject(jsonString, AlternateNamesObject.class);
        Assert.assertEquals("aaa", result.getSummary());
    }

    @Test
    public void jsonFileTest() {
        @Data
        @AllArgsConstructor
        class JsonUser {
            // java object 转 json时，key 使用 name123 而不是 name
            @JSONField(name = "name123")
            private String name;
            // java object 转 json时，不包括这个字段
            @JSONField(serialize = false)
            private String tag;
        }

        JsonUser user = new JsonUser("Jamie", "C");
        String jsonString = JSON.toJSONString(user);
        Assert.assertEquals("{\"name123\":\"Jamie\"}", jsonString);
    }

    @Data
    static class User {
        private String name;
        private String age;

        @JSONField(format = "yyyy-MM-dd")
        private Date pubTime;

        public User() {
        }

        public User(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }

    @Test
    public void jsonStaticMethodTest() {
        String jsonString = "{\"age\":\"100\",\"name\":\"tom\"}";
        String jsonArrayString = "[{\"name\":\"lily\",\"age\":12},{\"name\":\"lucy\",\"age\":15}]";

        //java object -> json string
        String userJsonStr = JSON.toJSONString(new User("tom", "100"));
        Assert.assertEquals(jsonString, userJsonStr);

        //json string -> json
        JSONObject userJson = JSON.parseObject(jsonString);
        Assert.assertEquals("100", userJson.getString("age"));
        Assert.assertEquals("tom", userJson.getString("name"));

        //json string -> java object
        User userObj = JSON.parseObject(jsonString, User.class);
        Assert.assertEquals("100", userObj.getAge());
        Assert.assertEquals("tom", userObj.getName());

        //string -> json array
        JSONArray jsonArray = JSON.parseArray(jsonArrayString);
        Assert.assertEquals(12, ((JSONObject) jsonArray.get(0)).get("age"));

        //json array -> string
        String jsonArrayStringResult = JSON.toJSONString(JSON.parseArray(jsonArrayString));
        Assert.assertEquals(jsonArrayString, jsonArrayStringResult);

        //json object -> java object
        User user = JSON.toJavaObject(JSON.parseObject(jsonString), User.class);
        Assert.assertEquals("tom", user.getName());
        Assert.assertEquals("100", user.getAge());

        //string to object(json array)
        Object arrayStr = JSON.parse("[\"a\",\"b\",\"c\"]");
        Assert.assertTrue(arrayStr instanceof JSONArray);

        Object jsonStr = JSON.parse("{\"k\" : \"v\"}");
        Assert.assertTrue(jsonStr instanceof JSONObject);
    }

    @Test
    public void jsonNonStaticMethodTest() {
        //map -> json object
        Map<String, Object> map = new HashMap<>();
        map.put("age", "24");
        map.put("name", "tim");
        JSONObject json = new JSONObject(map);
        Assert.assertEquals("24", json.getString("age"));
        Assert.assertEquals("tim", json.getString("name"));

        //json object -> string
        String jsonString = new JSONObject().toJSONString();
        Assert.assertEquals("{}", jsonString);

        //json object -> java object
        User user1 = json.toJavaObject(User.class);
        Assert.assertEquals("tim", user1.getName());
        Assert.assertEquals("24", user1.getAge());
    }

    @Test
    public void jsonFieldDateTest() throws ParseException {
        String jsonStr = "{\"pub_time\": \"2020-11-05\" }";
        // json 字符串日期 转 java object Date
        User user = JSON.parseObject(jsonStr, User.class);
        Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-05"), user.getPubTime());
    }

}
