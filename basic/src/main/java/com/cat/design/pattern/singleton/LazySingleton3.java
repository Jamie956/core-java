package com.cat.design.pattern.singleton;

/**
 * 懒汉式 -- 双检锁
 * - 定义私有静态变量的单例（未创建）
 * - 私有构造，不允许外部实例化
 * - 静态方法获取实例，实例不存在时创建。是加锁版的升级，先判断实例非空再上锁，再判空，而不是一开始就上锁
 * - `INSTANCE = new LazySingleton3()`对象的创建和赋值是两个指令，会有指令重排问题
 *
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