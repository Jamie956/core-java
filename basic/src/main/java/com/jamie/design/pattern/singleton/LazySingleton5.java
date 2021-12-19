package com.jamie.design.pattern.singleton;

/**
 * 懒汉式 -- 静态内部类
 * - 构造函数私有化，防止外部创建
 * - 定义静态内部类，创建它的静态私有变量单例
 * - 定义静态方法，获取实例，也就是访问静态内部类的静态变量
 */
public class LazySingleton5 {
    /* 私有构造方法，防止被实例化 */
    private LazySingleton5() {
        System.out.println("LazySingleton5 被创建了");
    }

    /* 此处使用一个内部类来维护单例 */
    private static class SingletonFactory {
        private static LazySingleton5 instance = new LazySingleton5();
    }

    /* 获取实例 */
    public static LazySingleton5 getInstance() {
        return SingletonFactory.instance;
    }

    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return getInstance();
    }
}
