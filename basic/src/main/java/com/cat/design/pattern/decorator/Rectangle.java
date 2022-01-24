package com.cat.design.pattern.decorator;

import lombok.Getter;

/**
 * 形状接口的实现类：正方形
 */
@Getter
public class Rectangle implements Shape {
    /**
     * 构造
     */
    public Rectangle() {
        System.out.println("正方形实例化");
    }

    /**
     * 画画
     */
    @Override
    public void draw() {
        System.out.println("画正方形");
    }
}
