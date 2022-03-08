package com.cat.pattern.builder;

/**
 * 包装接口实现：包装纸包装
 */
public class Wrapper implements Packing {
    /**
     * 用纸打包
     */
    @Override
    public String pack() {
        return "Wrapper";
    }
}
