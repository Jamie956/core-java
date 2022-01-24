package com.cat.design.pattern.builder;

/**
 * 汉堡的具体子类
 */
public class VegBurger extends Burger {
    /**
     * 给汉堡命名
     */
    @Override
    public String name() {
        return "Veg Burger";
    }

    /**
     * 给蔬菜汉堡定价
     */
    @Override
    public float price() {
        return 25.0f;
    }
}
