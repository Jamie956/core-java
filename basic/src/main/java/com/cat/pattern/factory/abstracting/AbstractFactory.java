package com.cat.pattern.factory.abstracting;

/**
 * 抽象工厂
 */
public abstract class AbstractFactory {
    /**
     * 获取实例，由子类实现
     */
    public abstract Color getColor(String color);
    /**
     * 获取实例，由子类实现
     */
    public abstract Shape getShape(String shape);
}