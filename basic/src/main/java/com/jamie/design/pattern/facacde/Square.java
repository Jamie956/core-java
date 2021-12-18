package com.jamie.design.pattern.facacde;

/**
 * 形状接口的实现类。正方形
 */
public class Square implements Shape {
    public Square() {
        System.out.println("Square 实例化");
    }

    /**
     * 实现接口方法，可以画正方形
     */
    @Override
    public void draw() {
        System.out.println("Draw Square");
    }
}