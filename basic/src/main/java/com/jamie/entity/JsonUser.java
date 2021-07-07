package com.jamie.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Zjm
 * @Date: 2021/7/7 15:48
 */
@AllArgsConstructor
@Data
public class JsonUser implements Serializable {
    private String name;
    private String age;

    //name -> userName
    @JSONField(name = "userName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}