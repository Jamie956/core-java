package com.cat.pattern.prototype;

/**
 * 继承抽象类的长方形类
 */
public class Rectangle extends Shape {
    public Rectangle () {
        type = "Rectangle";
        System.out.println("Rectangle 实例化");
    }

    /**
     * 继承父类方法
     */
    @Override
    void draw() {
        System.out.println("draw Rectangle");
    }
}