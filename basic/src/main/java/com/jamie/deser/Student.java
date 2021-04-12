package com.jamie.deser;


import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private static final long serialVersionUID = -4307074831333148448L;
    private String name;
        private int age;
    //transient 代表不序列化
//    private transient int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}


