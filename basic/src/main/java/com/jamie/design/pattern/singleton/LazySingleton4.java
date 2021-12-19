package com.jamie.design.pattern.singleton;

/**
 * 懒汉式 -- 双检锁+实例内存可见
 * - 定义私有静态变量的单例（未创建）
 * - 私有构造，不允许外部实例化
 * - 静态方法获取实例，实例不存在时创建。解决双检锁的指令重排，实例变量使用`volatile`保证变量的内存可见性，保证各CPU缓存里的变量保持一致。
 */
public class LazySingleton4 {
    //通过volatile修饰的变量，不会被线程本地缓存，所有线程对该对象的读写都会第一时间同步到主内存，从而保证多个线程间该对象的准确性
    private volatile static LazySingleton4 INSTANCE;

    private LazySingleton4() {
    }

    public static LazySingleton4 getInstance() {
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if (INSTANCE == null) {
            //同步块，线程安全的创建实例
            synchronized (LazySingleton4.class) {
                //再次检查实例是否存在，如果不存在才真正的创建实例
                if (INSTANCE == null) {
                    INSTANCE = new LazySingleton4();
                }
            }
        }
        return INSTANCE;
    }
}