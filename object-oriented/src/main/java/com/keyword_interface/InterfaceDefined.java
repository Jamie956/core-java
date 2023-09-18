package com.keyword_interface;

public interface InterfaceDefined {
    // 接口不能实例化，没有构造方法
//    InterfaceDefined ();

    void foo();

    // public 方法没有方法体
//    void bar() {};

    public void foo1();

    // default 同一个包的类可访问
    default void foo3() {}

}
