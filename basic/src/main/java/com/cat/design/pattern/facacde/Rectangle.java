package com.cat.design.pattern.facacde;

/**
 * 形状接口的实现类：正方形
 */
public class Rectangle implements Shape {
    public Rectangle() {
        System.out.println("Rectangle 实例化");
    }

    /**
     * 实现接口方法：正方形
     */
    @Override
    public void draw() {
        System.out.println("Draw Rectangle");
    }
}
