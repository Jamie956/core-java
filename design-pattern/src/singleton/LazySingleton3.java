package singleton;

/**
 * 懒汉式-静态内部类
 */
public class LazySingleton3 {
    private LazySingleton3() {
        System.out.println("1");
    }

    /**
     * 内部类来维护单例
     */
    private static class SingletonFactory {
        private static Single INSTANCE = new Single();
    }

    public static Single getInstance() {
        //初始访问静态内部类变量INSTANCE时，才会创建实例，再次访问不会再创建
        return SingletonFactory.INSTANCE;
    }
}
