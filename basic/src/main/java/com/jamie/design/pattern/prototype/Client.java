package com.jamie.design.pattern.prototype;

/**
 * 原型模式（Prototype Pattern）是用于创建重复的对象，
 * 属于创建型模式，
 * 它提供了一种创建对象的最佳方式。
 */
public class Client {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape shape = ShapeCache.getShape("1");
        System.out.println(shape.toString());
        shape.draw();
        Shape copyShape = ShapeCache.getShape("1");
        System.out.println(copyShape.toString());
        //克隆的新对象
        System.out.println(shape == copyShape);

        Shape shape2 = ShapeCache.getShape("2");
        System.out.println(shape2.toString());
        Shape shape3 = ShapeCache.getShape("3");
        System.out.println(shape3.toString());
    }
}
