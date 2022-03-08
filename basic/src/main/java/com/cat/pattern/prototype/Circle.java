package com.cat.pattern.prototype;

/**
 * 继承父类形状：圆
 */
public class Circle extends Shape {
    public Circle () {
        //赋值父类变量
        type = "Circle";
        System.out.println("Circle 实例化");
    }

    /**
     * 继承父类方法
     */
    @Override
    void draw() {
        System.out.println("draw Circle");
    }
}