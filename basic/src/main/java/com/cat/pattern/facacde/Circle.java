package com.cat.pattern.facacde;

/**
 * 形状接口的实现类：圆形
 */
public class Circle implements Shape {
    public Circle() {
        System.out.println("Circle 实例化");
    }

    /**
     * 实现接口方法：画圆形
     */
    @Override
    public void draw() {
        System.out.println("Draw Circle");
    }
}
