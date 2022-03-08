package com.cat.pattern.singleton;

import org.junit.Test;

/**
 *
 */
public class Client {

    /**
     * 并发获取饿汉实例，线程安全
     */
    @Test
    public void concurrentGetNoLockEagerSingletonTest() {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                EagerSingleton instance = EagerSingleton.getInstance();
                System.out.println(instance);
            }).start();
        }
    }

    /**
     * 并发获取懒汉实例，实例创建没有加锁，线程不安全
     * 重现并发问题
     * com.jamie.design.pattern.singleton.LazySingleton1@32f276b5
     * com.jamie.design.pattern.singleton.LazySingleton1@6a18032
     * com.jamie.design.pattern.singleton.LazySingleton1@6a18032
     * com.jamie.design.pattern.singleton.LazySingleton1@5a00783b
     * com.jamie.design.pattern.singleton.LazySingleton1@32f276b5
     */
    @Test
    public void concurrentGetNoLockLazySingletonTest(){
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                LazySingleton1 instance = LazySingleton1.getInstance();
                System.out.println(instance);
            }).start();
        }
    }

    /**
     * 并发获取懒汉实例，加锁，线程安全
     */
    @Test
    public void concurrentGetLockLazySingletonTest(){
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                LazySingleton2 instance = LazySingleton2.getInstance();
                System.out.println(instance);
            }).start();
        }
    }

    @Test
    public void concurrentGetLockLazySingletonTest2(){
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                LazySingleton3 instance = LazySingleton3.getInstance();
                System.out.println(instance);
            }).start();
        }
    }

    @Test
    public void concurrentGetInnerClassLazySingletonTest(){
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                LazySingleton5 instance = LazySingleton5.getInstance();
                System.out.println(instance);
            }).start();
        }
    }

    @Test
    public void enumSingletonTest() {
        Singleton instance = Singleton.Instance;
        Singleton instance2 = Singleton.Instance;

        Singleton hi = Singleton.Hi;
        Singleton hi2 = Singleton.Hi;

        System.out.println(instance == instance2);
        System.out.println(hi == hi2);
        System.out.println(instance == hi2);
    }
}
