package com.cat.pattern.singleton;

/**
 * 懒汉式-双检锁+实例内存可见
 */
public class LazySingleton4 {
    /**
     * volatile 保证可见性，防止指令重排
     */
    private volatile static LazySingleton4 INSTANCE;

    private LazySingleton4() {
    }

    public static LazySingleton4 getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleton4.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazySingleton4();
                }
            }
        }
        return INSTANCE;
    }
}