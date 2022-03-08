package com.cat.pattern.factory.reflection;

/**
 * 实现接口和方法
 */
public class Rectangle implements Shape {
    public Rectangle() {
    }
    @Override
    public void draw() {
        System.out.println("Draw Rectangle");
    }
}
