package com.jamie.design.pattern.factory.abstracting;

/**
 * 颜色接口的实现：绿色
 */
public class Green implements Color {
    /**
     * 填充绿色
     */
    @Override
    public void fill() {
        System.out.println("fill Green");
    }
}