package com.jamie.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String address;
    private Integer age;
    private String birthday;
    private String interests;
    @JSONField(format="yyyy-MM-dd")
    private Date left;

    public User(String name, String address, Integer age, String birthday, String interests) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.birthday = birthday;
        this.interests = interests;
    }
}
