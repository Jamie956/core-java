package com.jamie.design.pattern.bridge;

/**
 * 定义形状抽象类
 */
public abstract class Shape {
    protected DrawAPI drawApi;

    /**
     * 构造形状抽象类，入参画图类
     */
    protected Shape(DrawAPI drawApi) {
        this.drawApi = drawApi;
    }

    /**
     * 执行画图，由子类实现
     */
    abstract void draw();
}
