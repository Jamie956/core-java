package com.cat.design.pattern.builder;

/**
 * 食品的抽象实现：制作汉堡
 */
public abstract class Burger implements Item {
    /**
     * 给汉堡打包
     */
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    /**
     * 给汉堡定价抽象方法，有子类实现
     */
    @Override
    public abstract float price();
}
