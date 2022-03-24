package singleton;

/**
 * 懒汉式-双检锁+实例内存可见
 */
public class LazySingleton2 {
    /**
     * volatile 保证可见性，防止指令重排
     */
    private volatile static Single INSTANCE;

    private LazySingleton2() {
    }

    public static Single getInstance() {
        if (INSTANCE == null) {
            //类锁
            synchronized (Single.class) {
                //double check
                if (INSTANCE == null) {
                    //在Java指令中创建对象和赋值操作是分开进行，instance = new Singleton();是分两步执行
                    //volatile 防止指令重排
                    INSTANCE = new Single();
                }
            }
        }
        return INSTANCE;
    }
}