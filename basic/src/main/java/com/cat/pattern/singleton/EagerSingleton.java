package com.cat.pattern.singleton;

/**
 * 饿汉式
 */
public class EagerSingleton {
    /**
     * 静态变量，初始化时实例化对象
     */
    private final static EagerSingleton INSTANCE = new EagerSingleton();

    /**
     * 私有构造，外部类无法创建实例
     */
    private EagerSingleton() {
    }

    /**
     * 静态方法，返回实例对象
     */
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
