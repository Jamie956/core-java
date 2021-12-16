package com.jamie.design.pattern.factory.method;

public enum EnumActivityType {
    /**
     * 活动类型枚举
     */
    activityOne("one"),
    activityTwo("two");

    private String name;

    EnumActivityType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}