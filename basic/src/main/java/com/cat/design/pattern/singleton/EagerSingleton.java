package com.cat.design.pattern.singleton;

/**
 * 饿汉式
 * - 创建私有静态变量的单例对象
 * - 构造方法私有，不允许外部构造
 * - 提供获取实例的静态方法，返回静态变量
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
