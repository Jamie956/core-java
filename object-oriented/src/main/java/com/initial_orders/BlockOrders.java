package com.initial_orders;

// orders: static block -> not static block -> construct
public class BlockOrders {
    private static String staticValue = "a";
    private String notStaticValue = "a";

    static {
        System.out.println("static block");
        // 静态代码块能访问静态变量
        String s1 = staticValue;
        // 静态代码块不能访问非静态变量
//        String s2 = notStaticValue;
    }

    {
        System.out.println("not static block");
        // 非静态代码块能访问静态变量
        String s1 = staticValue;
        // 非静态代码块能访问非静态变量
        String s2 = notStaticValue;
    }

    public BlockOrders() {
        System.out.println("construct");
    }

    public static void main(String[] args) {
        new BlockOrders();
    }
}
