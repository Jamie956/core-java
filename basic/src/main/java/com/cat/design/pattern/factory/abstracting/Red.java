package com.cat.design.pattern.factory.abstracting;

/**
 * 颜色接口的实现：红色
 */
public class Red implements Color {
    /**
     * 填充红色
     */
    @Override
    public void fill() {
        System.out.println("fill Red");
    }
}
