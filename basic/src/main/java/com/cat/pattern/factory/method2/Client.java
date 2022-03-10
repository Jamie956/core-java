package com.cat.pattern.factory.method2;

/**
 * 工厂方法模式
 */
public class Client {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        factory.register(new Circle());
        factory.register(new Rectangle());

        Shape c = factory.getInstance("key-circle");
        c.process("a");

        Shape r = factory.getInstance("key-rectangle");
        r.process("b");
    }
}
