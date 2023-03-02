package com.example.keyword_interface;

public interface Dualist {
    // 接口不能实例化，没有构造方法
    // Not allowed in interface
//    Dualist ();

    void foo();

    // public 方法没有方法体
    // Interface abstract methods cannot have body
//    void bar() {};

    public void foo1();

    default void foo3() {}

}
