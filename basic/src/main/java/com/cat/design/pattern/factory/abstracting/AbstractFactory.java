package com.cat.design.pattern.factory.abstracting;

/**
 * 抽象工厂
 */
public abstract class AbstractFactory {
    /**
     * 获取颜色实例抽象方法，由子类实现
     */
    public abstract Color getColor(String color);
    /**
     * 获取形状实例抽象方法，由子类实现
     */
    public abstract Shape getShape(String shape);
}