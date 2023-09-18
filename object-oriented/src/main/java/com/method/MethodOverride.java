package com.method;

// 方法重写
public class MethodOverride {
    public static void main(String[] args) {
        System.out.println(new Child().bar(""));
    }
}

class Parent {
    public String bar(String s) {
        return "parent";
    }
}

class Child extends Parent{
    // 方法重写
    // 返回值类型、方法名和参数列表 与父类方法保持一致
    // @Override 表示重写
    // 访问修饰符权限 public 不能小于父类
    @Override
    public String bar(String s) {
        return "child";
    }
}