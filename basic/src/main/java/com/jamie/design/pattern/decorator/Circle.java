package com.jamie.design.pattern.decorator;

import lombok.Getter;

/**
 * 形状接口的实现类：圆形
 */
@Getter
public class Circle implements Shape {
    /**
     * 构造
     */
    public Circle() {
        System.out.println("圆实例化了");
    }

    /**
     * 画画
     */
    @Override
    public void draw() {
        System.out.println("画圆");
    }
}
