package com.cat.pattern.builder;

/**
 * 打包接口的实现：瓶装
 */
public class Bottle implements Packing {
    /**
     * 用瓶打包
     */
    @Override
    public String pack() {
        return "Bottle";
    }
}
