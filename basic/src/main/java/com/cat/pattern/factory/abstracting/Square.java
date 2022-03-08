package com.cat.pattern.factory.abstracting;

/**
 * 形状接口的实现：长方形
 */
public class Square implements Shape {
    /**
     * 画长方形
     */
    @Override
    public void draw() {
        System.out.println("Draw Square");
    }
}