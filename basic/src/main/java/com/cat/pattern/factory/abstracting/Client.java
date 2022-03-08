package com.cat.pattern.factory.abstracting;

/**
 * 抽象工厂模式
 * 抽象工厂创建具体工厂（共同抽象类），具体工厂创建实例对象（共同接口）
 */
public class Client {
    public static void main(String[] args) {
        //具体工厂的创建：形状工厂
        AbstractFactory shape = FactoryProducer.getFactory("SHAPE");
        Shape rectangle = shape.getShape("Rectangle");
        rectangle.draw();
        Shape square = shape.getShape("Square");
        square.draw();
        Shape circle = shape.getShape("Circle");
        circle.draw();

        //具体工厂的创建：颜色工厂
        AbstractFactory color = FactoryProducer.getFactory("COLOR");
        Color red = color.getColor("Red");
        red.fill();
        Color blue = color.getColor("Blue");
        blue.fill();
        Color green = color.getColor("Green");
        green.fill();
    }
}
