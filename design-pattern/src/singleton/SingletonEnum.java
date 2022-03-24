package singleton;

/**
 * 使用枚举来实现单实例控制会更加简洁，而且JVM从根本上提供保障，
 * 绝对防止多次实例化，是更简洁、高效、安全的实现单例的方式
 */
public enum SingletonEnum {
    /**
     * 定义一个枚举的元素，它就代表一个单例
     */
    Instance("1", "a");

    /**
     * 第一次访问 Instance 变量时才会构造实例
     */
    SingletonEnum(String id, String name) {
        System.out.println("1");
    }
}
