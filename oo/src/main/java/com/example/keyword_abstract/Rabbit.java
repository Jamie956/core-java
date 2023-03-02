package com.example.keyword_abstract;

// 可以定义具体方法、构造方法、抽象方法、普通属性、静态属性、静态方法
public abstract class Rabbit {
    private String s;
    private static String b;
    Rabbit() {}

    // 如果一个类有抽象方法，就一定是抽象类
    abstract void foo();

    public void concrete() {}
    public static void concreteS() {}
}
