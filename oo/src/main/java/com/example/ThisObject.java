package com.example;

public class ThisObject {
    private String s = "str";

    public void foo() {
        System.out.println("foo");
    }

    public void bar() {
        // this 表示当前对象，可以调用方法、调用全局变量
        this.foo();
        System.out.println(this.s);
    }

    public static void main(String[] args) {
        new ThisObject().bar();
    }
}
