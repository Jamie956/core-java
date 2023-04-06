package com.example.inner_class;

// 静态内部类特性
public class DefinedInnerStaticClass {

    public static void main(String[] args) {
        // 可以直接创建静态内部类，不需要先创建外部类
        InnerStaticClass o = new InnerStaticClass();
        // 先实例化静态内部类再访问内部类非静态变量
        int a = new InnerStaticClass().innerNonStaticVar;
        // 访问静态内部类静态变量不需要实例化
        int b = InnerStaticClass.innerStaticVar;
    }

    public int outerNonStaticVar = 1;
    public static int outerStaticVar = 1;
    public void outerNonStaticMethod() {}
    public static void outerStaticMethod() {}

    static class InnerStaticClass {
        // 静态内部类定义静态全局变量和非静态全局变量
        private int innerNonStaticVar = 3;
        private static int innerStaticVar = 4;
        // 静态内部类不能使用外部类的非静态变量
//        public int a = outerNonStaticVar;
        // 静态内部类不能使用外部类的静态变量
        public int b = outerStaticVar;

        // 静态内部类可以定义静态方法
        public static void innerStaticMethod(){
        }
        // 静态内部类可以定义非静态方法
        public void innerNonStaticMethod(){
            outerStaticMethod();
        }
    }
}
