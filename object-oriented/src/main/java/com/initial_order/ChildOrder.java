package com.initial_order;

public class ChildOrder extends ParentOrder {
    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类代码块");
    }

    public ChildOrder() {
        System.out.println("子类构造函数");
    }
}
