package com.cat.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public class GsonGson {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class User implements Serializable {
        private static final long serialVersionUID = -3307269962764425802L;
        private Integer id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        User user = gson.fromJson("{'id':1,'name':'tim'}", User.class);

        JsonObject json = new JsonParser().parse("{'name':'tim','age':26}").getAsJsonObject();
    }
}
