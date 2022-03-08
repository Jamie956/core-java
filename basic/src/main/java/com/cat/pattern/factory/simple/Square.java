package com.cat.pattern.factory.simple;

/**
 * 实现接口和方法
 */
public class Square implements Shape {
    public Square() {
    }

    @Override
    public void draw() {
        System.out.println("Draw Square");
    }
}
