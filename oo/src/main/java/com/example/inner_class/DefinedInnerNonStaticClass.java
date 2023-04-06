package com.example.inner_class;

// 非静态内部类特性
public class DefinedInnerNonStaticClass {
    public static void main(String[] args) {
        // 外部类访问内部类
        InnerNonStaticClass n = new DefinedInnerNonStaticClass().new InnerNonStaticClass();
    }

    public int i = 1;
    public void ofoo() {}

    class InnerNonStaticClass {
        // 非静态内部类不能有静态变量
//        public static String a = "a";
        public String b = "b";
        // 非静态内部类可以定义 final static，放常量池
        public final static String c = "c";

        // 非静态内部类不能有静态方法
//        public static void foo1(){}

        // 非静态内部类访问外部类的非静态变量
        public int df = i;

        public void foo2(){
            // 非静态内部类访问外部类非静态方法
            ofoo();
        }
    }
}
