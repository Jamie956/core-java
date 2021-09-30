package com.jamie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Person implements Serializable {
    private static final long serialVersionUID = -2687432631518129972L;

    private String name;
    private Double length;

    public Person() {
        this.name = "tom";
    }

    public Person(String name) {
        this.name = name;
    }
}
