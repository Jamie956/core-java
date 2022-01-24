package com.cat.design.pattern.builder;

/**
 * 汉堡的具体子类：鸡堡
 */
public class ChickenBurger extends Burger {
    /**
     * 给汉堡命名
     */
    @Override
    public String name() {
        return "Chicken Burger";
    }

    /**
     * 给汉堡定价
     */
    @Override
    public float price() {
        return 50.0f;
    }
}