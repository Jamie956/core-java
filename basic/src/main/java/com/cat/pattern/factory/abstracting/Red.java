package com.cat.pattern.factory.abstracting;

public class Red implements Color {
    /**
     * 实现接口方法
     */
    @Override
    public void fill() {
        System.out.println("fill Red");
    }
}
