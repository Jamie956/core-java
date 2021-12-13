package com.jamie.design.pattern.singleton;

/**
 * 懒汉式：加锁
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
