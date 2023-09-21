package com.initial_order;

public class ParentOrder {
    static {
        System.out.println("父类静态代码块");
    }

    {
        System.out.println("父类代码块");
    }

    ParentOrder() {
        System.out.println("父类构造函数");
    }
}