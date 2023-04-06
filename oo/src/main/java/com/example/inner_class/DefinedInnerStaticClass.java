package com.example.inner_class;

// 静态内部类特性
public class DefinedInnerStaticClass {

    public static void main(String[] args) {
        // 可以直接创建静态内部类，不需要先创建外部类
        InnerStaticClass o = new InnerStaticClass();
        String b = o.b;
    }

    public int i = 1;
    public void ofoo() {}

    static class InnerStaticClass {
        // 静态内部类可以有静态全局变量
        public static String a = "a";
        public String b = "b";
        // 静态内部类不能使用任何外部类的非静态变量
//        public int d = i;

        // 静态内部类可以有静态方法
        public static void foo1(){
        }

        // 静态内部类可以有非静态方法
        public void foo2(){}
    }
}
