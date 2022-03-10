package com.cat.pattern.factory.simple;

/**
 * 工厂模式-静态
 * 缺点：每增加一种类型都要去修改工厂方法，违背开闭原则
 */
public class Client {
    public static void main(String[] args) {
        Factory factory = new Factory();

        Shape square = factory.getInstance("Square");
        square.draw();

        Shape circle = factory.getInstance("Circle");
        circle.draw();
    }
}
