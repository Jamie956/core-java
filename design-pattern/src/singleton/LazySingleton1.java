package singleton;

/**
 * 懒汉式-加锁，线程安全
 */
public class LazySingleton1 {
    /**
     * 静态变量
     */
    private static Single INSTANCE;

    /**
     * 私有构造，不允许外部实例化
     */
    private LazySingleton1() {
    }

    /**
     * 获取实例，实例不存在时创建
     * 对象锁，不允许其他线程修改此对象
     */
    public static synchronized Single getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Single();
        }
        return INSTANCE;
    }
}
