package com.jamie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jamie.entity.JsonUser;

public class FastJsonTest {

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

    //json object -> json string

    //
}


