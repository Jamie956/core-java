package com.cat.pattern.factory.abstracting;

/**
 * 抽象工厂模式
 */
public class Client {
    public static void main(String[] args) {
        AbstractFactory sf = FactoryProducer.getFactory("SHAPE");
        Shape rectangle = sf.getShape("Rectangle");
        rectangle.draw();
        Shape circle = sf.getShape("Circle");
        circle.draw();


        AbstractFactory cf = FactoryProducer.getFactory("COLOR");
        Color red = cf.getColor("Red");
        red.fill();
        Color blue = cf.getColor("Blue");
        blue.fill();
    }
}
