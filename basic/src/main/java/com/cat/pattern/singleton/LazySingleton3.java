package com.cat.pattern.singleton;

/**
 * 懒汉式-双检锁
 */
public class LazySingleton3 {
    private static LazySingleton3 INSTANCE;

    private LazySingleton3() {
    }

    public static LazySingleton3 getInstance() {
        //先检查实例是否存在
        if (INSTANCE == null) {
            //线程安全的创建实例
            synchronized (LazySingleton3.class) {
                //再次检查实例是否存在
                if (INSTANCE == null) {
                    //在Java指令中创建对象和赋值操作是分开进行，instance = new Singleton();是分两步执行
                    //有指令重排问题
                    INSTANCE = new LazySingleton3();
                }
            }
        }
        return INSTANCE;
    }
}