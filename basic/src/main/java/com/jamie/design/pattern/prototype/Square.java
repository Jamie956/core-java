package com.jamie.design.pattern.prototype;

/**
 * 继承父类的正方形
 */
public class Square extends Shape {
    public Square () {
        type = "Square";
        System.out.println("Square 实例化");
    }

    /**
     * 父类抽象方法的实现
     */
    @Override
    void draw() {
        System.out.println("draw Square");
    }
}
