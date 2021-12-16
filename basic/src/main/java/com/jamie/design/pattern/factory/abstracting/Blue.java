package com.jamie.design.pattern.factory.abstracting;

/**
 * 颜色接口的实现：蓝色
 */
public class Blue implements Color {
    /**
     * 填充红色
     */
    @Override
    public void fill() {
        System.out.println("fill Blue");
    }
}