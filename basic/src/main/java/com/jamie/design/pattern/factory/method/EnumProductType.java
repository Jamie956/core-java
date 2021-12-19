package com.jamie.design.pattern.factory.method;

/**
 * 枚举实体类型
 */
public enum EnumProductType {
    productOne("one"),
    productTwo("two");

    private String name;

    EnumProductType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}