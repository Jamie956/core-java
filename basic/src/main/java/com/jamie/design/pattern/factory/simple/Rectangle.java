package com.jamie.design.pattern.factory.simple;

/**
 * 实现形状接口的类：长方形
 */
public class Rectangle implements Shape {
    public Rectangle() {
        System.out.println("Rectangle 实例化");
    }

    /**
     * 接口方法的实现：画长方形
     */
    @Override
    public void draw() {
        System.out.println("Draw Rectangle");
    }
}
