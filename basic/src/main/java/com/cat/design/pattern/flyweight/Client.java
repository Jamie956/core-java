package com.cat.design.pattern.flyweight;

/**
 * 享元模式（Flyweight Pattern）主要用于减少创建对象的数量，
 * 以减少内存占用和提高性能。这种类型的设计模式属于结构型模式，
 * 它提供了减少对象数量从而改善应用所需的对象结构的方式。
 * 从缓存读取实例
 */
public class Client {
    public static void main(String[] args) {
        Circle circle = (Circle) ShapeFactory.getCircle("Red");
        circle.setX(1);
        circle.setY(2);
        circle.setRadius(3);
        circle.draw();

        Circle circle2 = (Circle) ShapeFactory.getCircle("Red");
        circle2.draw();

        System.out.println(circle == circle2);
    }
}
