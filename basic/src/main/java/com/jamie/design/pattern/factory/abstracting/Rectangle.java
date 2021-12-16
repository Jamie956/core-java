package com.jamie.design.pattern.factory.abstracting;

/**
 * 形状接口的实现：正方形
 */
public class Rectangle implements Shape {
    /**
     * 画正方形
     */
    @Override
    public void draw() {
        System.out.println("Draw Rectangle");
    }
}