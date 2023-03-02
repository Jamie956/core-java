package com.example;

public class MethodOverride {
    public static void main(String[] args) {
        System.out.println(new Child().bar(""));
    }
}

class Parent {
    public String bar(String s) {
        return "parent bar";
    }
}

class Child extends Parent{
    // 返回值类型、方法名和参数列表 与父类方法保持一致
    // @Override 表示重写
    // public 访问权限不小于父类
    @Override
    public String bar(String s) {
        return "child bar";
    }
}