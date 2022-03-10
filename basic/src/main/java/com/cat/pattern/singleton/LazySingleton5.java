package com.cat.pattern.singleton;

/**
 * 懒汉式-静态内部类
 */
public class LazySingleton5 {
    private LazySingleton5() {
        System.out.println("1");
    }

    /**
     * 使用一个内部类来维护单例
     */
    private static class SingletonFactory {
        private static LazySingleton5 INSTANCE = new LazySingleton5();
    }

    public static LazySingleton5 getInstance() {
        //初始获取静态内部类变量INSTANCE时，才会初始化变量，构造实例
        return SingletonFactory.INSTANCE;
    }
}
