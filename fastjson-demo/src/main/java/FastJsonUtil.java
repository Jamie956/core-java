import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;

public class FastJsonUtil {
    @Data
    @AllArgsConstructor
    static class JsonUser {
        //定义转成JSON对应字段的名字
        @JSONField(name = "jsonFieldCustomName")
        private String name;
        private String age;
        //字段不转成JSON
        @JSONField(serialize = false)
        private List<String> tags;
    }

    @Data
    static class JsonUser2 {
        //json 字段多别名，可以将abstract或summary 的key value 设置到对象
        @JSONField(alternateNames = {"abstract", "summary"})
        private String summary;
    }

    @Test
    public void multiField() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("abstract", "content1");
        String str = jsonObject.toJSONString();

        JsonUser2 jsonUser2 = JSON.parseObject(str, JsonUser2.class);
        System.out.println(jsonUser2);
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
    @Test
    public void convert() {
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
        String jsonString = new JSONObject().toJSONString();

        //json -> java object
        User user1 = json.toJavaObject(User.class);
        User user2 = JSON.toJavaObject(json, User.class);

        //string -> json array
        JSONArray jsonArray = JSON.parseArray("[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]");
        //json array -> string
        String jsonString123 = JSON.toJSONString(new JSONArray());

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

    @Test
    public void jsonStrTypeTest() {
        String s1 = "[\"a\",\"b\",\"c\"]";
        String s2 = "{\"aaa\" : \"111\"}";
        String s3 = "{\"aaa\" : \"111\"";
        String s4 = "asdasfsdg";

        System.out.println(s1.getClass().getTypeName());
        System.out.println(s2.getClass().getTypeName());
        System.out.println(s3.getClass().getTypeName());
        System.out.println(s4.getClass().getTypeName());

        Object o1 = checkAndConvertStrToJson("[\"a\",\"b\",\"c\"]");
        Object o2 = checkAndConvertStrToJson("{\"aaa\" : \"111\"}");
        Object o3 = checkAndConvertStrToJson("{\"aaa\" : \"111\"");
        Object o4 = checkAndConvertStrToJson("asdasfsdg");

        System.out.println("-------------------");
        System.out.println(o1.getClass().getTypeName());
        System.out.println(o2.getClass().getTypeName());
        System.out.println(o3.getClass().getTypeName());
        System.out.println(o4.getClass().getTypeName());
    }

    /**
     * 字符串转换成 json object 或者json array 或者字符串
     */
    public static Object checkAndConvertStrToJson(String jsonStr) {
        Object object;
        try {
            object = JSON.parse(jsonStr);
        } catch (Exception e) {
            return jsonStr;
        }
        return object;
    }
}
