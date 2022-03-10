package com.cat.pattern.factory.method;

/**
 * 工厂方法模式
 */
public class Client {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape s = factory.shape("circle", "ccc");
        System.out.println(s.getName());
    }
}
