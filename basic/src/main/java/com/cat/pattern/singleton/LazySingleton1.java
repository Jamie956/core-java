package com.cat.pattern.singleton;

/**
 * 懒汉式-线程不安全
 */
public class LazySingleton1 {
    /**
     * 静态变量
     */
    private static LazySingleton1 INSTANCE;

    /**
     * 私有构造，不允许外部实例化
     */
    private LazySingleton1() {
    }

    /**
     * 静态方法获取实例，实例不存在时创建。多线程环境有线程安全问题
     */
    public static LazySingleton1 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton1();
        }
        return INSTANCE;
    }
}
