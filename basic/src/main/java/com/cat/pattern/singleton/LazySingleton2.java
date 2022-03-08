package com.cat.pattern.singleton;

/**
 * 懒汉式 -- 加锁版本
 * - 定义私有静态变量的单例（未创建）
 * - 私有构造，不允许外部实例化
 * - 静态方法获取实例，实例不存在时创建，与线程不安全不同的是，方法加了锁保证线程安全，但降低了效率
 */
public class LazySingleton2 {
    private static LazySingleton2 INSTANCE;

    private LazySingleton2() {
    }

    public static synchronized LazySingleton2 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton2();
        }
        return INSTANCE;
    }
}
