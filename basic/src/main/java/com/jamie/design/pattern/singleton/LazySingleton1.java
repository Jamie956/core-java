package com.jamie.design.pattern.singleton;

/**
 * 懒汉式：线程不安全版本，不加锁有线程安全问题
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
