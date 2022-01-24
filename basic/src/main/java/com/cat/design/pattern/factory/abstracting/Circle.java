package com.cat.design.pattern.factory.abstracting;

/**
 * 形状接口的实现：圆形
 */
public class Circle implements Shape {
    /**
     * 画圆形
     */
    @Override
    public void draw() {
        System.out.println("Draw Circle");
    }
}
