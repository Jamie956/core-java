package com.jamie.design.pattern.singleton;

/**
 * 懒汉式：双检锁
 */
public class LazySingleton3 {
    private static LazySingleton3 INSTANCE;

    private LazySingleton3() {
    }

    public static LazySingleton3 getInstance() {
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if (INSTANCE == null) {
            //同步块，线程安全的创建实例
            synchronized (LazySingleton3.class) {
                //再次检查实例是否存在，如果不存在才真正的创建实例
                if (INSTANCE == null) {
                    //在Java指令中创建对象和赋值操作是分开进行的，也就是说instance = new Singleton();语句是分两步执行的。
                    //会有指令重排问题
                    INSTANCE = new LazySingleton3();
                }
            }
        }
        return INSTANCE;
    }
}