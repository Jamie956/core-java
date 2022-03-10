package com.cat.pattern.singleton;

/**
 * 懒汉式-加锁
 */
public class LazySingleton2 {
    /**
     * 静态变量
     */
    private static LazySingleton2 INSTANCE;

    /**
     * 私有构造，不允许外部实例化
     */
    private LazySingleton2() {
    }

    /**
     * 静态方法获取实例，实例不存在时创建
     */
    public static synchronized LazySingleton2 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton2();
        }
        return INSTANCE;
    }
}
