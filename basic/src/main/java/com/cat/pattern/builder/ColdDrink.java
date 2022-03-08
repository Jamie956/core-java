package com.cat.pattern.builder;

/**
 * 食品的抽象实现：冷饮
 */
public abstract class ColdDrink implements Item {
    /**
     * 给冷饮打包
     */
    @Override
    public Packing packing() {
        return new Bottle();
    }

    /**
     * 给冷饮标价抽象方法：由具体子类实现
     */
    @Override
    public abstract float price();
}