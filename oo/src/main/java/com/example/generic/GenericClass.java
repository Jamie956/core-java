package com.example.generic;

// 泛型类
public class GenericClass<T> {
    // 类型T 由外部指定
    private T x;

    public void foo(T value) {}
    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }
}
