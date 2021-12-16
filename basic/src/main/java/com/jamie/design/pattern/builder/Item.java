package com.jamie.design.pattern.builder;

/**
 * 食品制作接口
 */
interface Item {
    /**
     * 制定食品名
     */
    String name();

    /**
     * 打包方式
     */
    Packing packing();

    /**
     * 制定价格
     */
    float price();
}
