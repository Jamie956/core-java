package com.cat.pattern.builder;

/**
 * 冷饮的具体实现子类
 */
public class Pepsi extends ColdDrink {
    /**
     * 给百事命名
     */
    @Override
    public String name() {
        return "Pepsi";
    }

    /**
     * 给百事定价
     */
    @Override
    public float price() {
        return 35.0f;
    }
}
