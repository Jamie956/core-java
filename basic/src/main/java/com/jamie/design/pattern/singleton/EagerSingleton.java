package com.jamie.design.pattern.singleton;

/**
 * 饿汉式
 */
public class EagerSingleton {
    private final static EagerSingleton INSTANCE = new EagerSingleton();

    /**
     * 私有构造，外部类无法创建实例
     */
    private EagerSingleton() {
    }

    /**
     * 静态方法get
     */
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
