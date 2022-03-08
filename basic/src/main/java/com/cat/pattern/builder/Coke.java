package com.cat.pattern.builder;

/**
 * 冷饮的具体子类：可乐
 */
public class Coke extends ColdDrink {
    /**
     * 给可乐命名
     */
    @Override
    public String name() {
        return "Coke";
    }

    /**
     * 给可乐定价
     */
    @Override
    public float price() {
        return 30.0f;
    }
}
