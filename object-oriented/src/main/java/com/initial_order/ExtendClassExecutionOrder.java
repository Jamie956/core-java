package com.initial_order;

// 父类和子类的代码块和构造函数执行顺序
// 父类静态代码块 -> 子类静态代码块 -> 父类代码块 -> 父类构造函数 -> 子类代码块 -> 子类构造函数
public class ExtendClassExecutionOrder {
    public static void main(String[] args) {
        // 静态代码块只加载一次
        new ChildOrder();
        System.out.println("-----------------再次创建子类-----------------");
        new ChildOrder();
    }
}
