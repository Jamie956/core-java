package com.jamie.design.pattern.Facory.method2;

/**
 * 分享模版枚举
 */
public enum EnumShareType{
    /**
     * 分享类型
     */
    SUCCESS_ORDER("successOrder");

    private String name;

    EnumShareType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
