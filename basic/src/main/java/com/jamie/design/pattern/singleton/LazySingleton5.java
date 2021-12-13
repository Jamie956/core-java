package com.jamie.design.pattern.singleton;

/**
 * 懒汉式：静态内部类
 * 来维护单例的实现，JVM内部的机制能够保证当一个类被加载的时候，这个类的加载过程是线程互斥的。
 * 这样当我们第一次调用getInstance的时候，JVM能够帮我们保证instance只被创建一次，并且会保证把赋值给instance的内存初始化完毕
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
