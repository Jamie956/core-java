package com.example;

public class KeywordStatic {
    public static void main(String[] args) {
        StaticMethodAndVar o = new StaticMethodAndVar();
        System.out.println(StaticMethodAndVar.s);
        StaticMethodAndVar.foo();
    }
}

class StaticMethodAndVar {
    // 修饰代码块，可用于类的初始化操作，提升程序的性能
    static {
        System.out.println("static block init");
    }

    // static声明的成员变量为静态成员变量，生命周期和类相同，在整个程序执行期间都有效
    public static String s = "str";

    // static修饰的方法为静态方法，能直接调用；静态方法不依赖任何对象就可以直接访问
    public static void foo() {
        System.out.println("foo");
    }

}