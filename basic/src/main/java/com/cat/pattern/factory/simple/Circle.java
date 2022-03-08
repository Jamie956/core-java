package com.cat.pattern.factory.simple;

/**
 * 实现形状接口的类：圆
 */
public class Circle implements Shape {
    public Circle() {
        System.out.println("Circle 实例化");
    }

    /**
     * 实现接口方法，画圆
     */
    @Override
    public void draw() {
        System.out.println("Draw Circle");
    }
}
