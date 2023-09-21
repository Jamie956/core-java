package com.generic;

// 对象创建限定了泛型类型 T，类中出现的 T（变量类型，方法参数类型，方法返回值类型） 都是同一个类型
public class GenericClass<T> {
    private T x;

    public void foo(T value) {}
    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }
}
