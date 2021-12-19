package com.jamie.design.pattern.singleton;

/**
 * 懒汉式 -- 线程不安全版本
 * - 定义私有静态变量的单例（未创建）
 * - 私有构造，不允许外部实例化
 * - 静态方法获取实例，实例不存在时创建。方法无锁会有线程安全问题，导致创建了多个实例
 */
public class LazySingleton1 {
    private static LazySingleton1 INSTANCE;

    private LazySingleton1() {
    }

    public static LazySingleton1 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton1();
        }
        return INSTANCE;
    }
}
