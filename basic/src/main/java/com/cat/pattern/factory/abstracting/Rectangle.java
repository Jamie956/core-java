package com.cat.pattern.factory.abstracting;

public class Rectangle implements Shape {
    /**
     * 实现接口方法
     */
    @Override
    public void draw() {
        System.out.println("Draw Rectangle");
    }
}