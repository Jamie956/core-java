package singleton;

// 饿汉式，线程安全
public class EagerSingleton {
    // 静态变量，创建实例时就实例化对象
    private final static Single INSTANCE = new Single();
    // 私有构造，外部类无法创建实例
    private EagerSingleton() {
    }
    // 静态方法，返回实例对象
    public static Single getInstance() {
        return INSTANCE;
    }
}
