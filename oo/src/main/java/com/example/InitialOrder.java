package com.example;

/*
static var: s
static block
not static var: a
block
construct
 */
public class InitialOrder {
    private static String s = "s";
    private String a = "a";

    static {
        System.out.println("static var: " + s);
        System.out.println("static block");
    }

    {
        System.out.println("not static var: " + a);
        System.out.println("block");
    }

    public InitialOrder() {
        System.out.println("construct");
    }

    public static void main(String[] args) {
        new InitialOrder();
    }
}
