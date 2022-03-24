package factory.reflection;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂
 */
public class Factory {
    /**
     * 缓存的 key-Class对象
     */
    private static final Map<String, Class<?>> CACHE = new HashMap<>();

    /**
     * 缓存 key-Class对象
     */
    public static void register(String key, Class<?> clazz) {
        CACHE.put(key, clazz);
    }

    /**
     * 根据key 反射构造Class的实例
     */
    public static Shape cache(String key) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = CACHE.get(key);
        return (Shape) clazz.newInstance();
    }
}
