package com.cat.pattern.factory.reflection;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂
 */
public class Factory {
    /**
     * 缓存 key-Class对象
     */
    private static final Map<String, Class<?>> CACHE = new HashMap<>();

    /**
     * 注册 Class对象
     */
    public static void register(String key, Class<?> clazz) {
        CACHE.put(key, clazz);
    }

    /**
     * 根据注册key，反射构造Class的实例
     */
    public static Shape getInstance(String key) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = CACHE.get(key);
        return (Shape) clazz.newInstance();
    }
}
