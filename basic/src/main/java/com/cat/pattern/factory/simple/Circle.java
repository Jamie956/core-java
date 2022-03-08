package com.cat.pattern.factory.simple;

/**
 * 实现接口和方法
 */
public class Circle implements Shape {
    public Circle() {
    }

    @Override
    public void draw() {
        System.out.println("Draw Circle");
    }
}
