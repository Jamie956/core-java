package com.keyword_abstract;

public abstract class KeywordAbstract {
    // 可以定义变量
    private String s;

    // 可以定义静态变量
    private static String b;

    // 可以定义构造方法
    KeywordAbstract() {}

    // 可以定义抽象方法
    // 如果一个类有抽象方法，就一定是抽象类
    abstract void foo();

    // 可以定义具体方法
    public void concrete() {}

    // 可以定义静态方法
    public static void concreteS() {}

    public static void main(String[] args) {
        // 不能实例化
//        KeywordAbstract o = new KeywordAbstract();
    }
}
