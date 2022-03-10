package com.cat.pattern.factory.method2;

/**
 * 模板
 */
public class Rectangle implements Shape {
    /**
     * 获取 key
     */
    @Override
    public String getKey() {
        return "key-rectangle";
    }

    @Override
    public void process(String name) {
        System.out.println((getKey() + " process " + name));
    }
}
