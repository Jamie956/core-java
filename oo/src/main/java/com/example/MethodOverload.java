package com.example;

public class MethodOverload {
    // 方法参数类型不同，可重载
    public void foo(int i) {}
    public void foo(String s) {}

    // 方法参数个数不同，可重载

    // 方法参数排序不同，可重载
    public void foo(int i, String s) {}
    public void foo(String s, int i) {}

}
