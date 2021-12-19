package com.jamie.design.pattern.factory.simple;

/**
 * 实现接口的类：正方形
 */
public class Square implements Shape {
    public Square() {
        System.out.println("Square 实例化");
    }

    /**
     * 实现接口方法，画正方形
     */
    @Override
    public void draw() {
        System.out.println("Draw Square");
    }
}
