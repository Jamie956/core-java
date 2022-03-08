package com.cat.pattern.factory.simple;

/**
 * 简单工厂模式 -- 静态工厂模式
 * 缺点：每增加一种类型都要去修改工厂方法，违背开闭原则
 */
public class Client {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape rectangle = shapeFactory.getShape("Rectangle");
        Shape rectangle2 = shapeFactory.getShape("Rectangle");
        System.out.println(rectangle == rectangle2);
        rectangle.draw();

        Shape square = shapeFactory.getShape("Square");
        square.draw();

        Shape circle = shapeFactory.getShape("Circle");
        circle.draw();
    }
}
