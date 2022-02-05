package com.cat.pojo;

import java.util.List;

public class Category {
    private int id;
    private String name;
    List<Product> products;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}