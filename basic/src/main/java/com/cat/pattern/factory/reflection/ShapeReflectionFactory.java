package com.cat.pattern.factory.reflection;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂（反射）：缓存对象class，根据class反射构造实例
 * 缺点：反射机制会降低程序的性能
 */
public class ShapeReflectionFactory {
    /**
     * 缓存 key-Class对象
     */
    private static final Map<String, Class<?>> ACTIVITY_MAP = new HashMap<>();

    /**
     * 注册 Class对象class
     */
    public static void register(String type, Class<?> productClazz) {
        ACTIVITY_MAP.put(type, productClazz);
    }

    /**
     * 根据key，获取Class对象，反射构造Class的实例
     */
    public static Shape getObject(String type) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = ACTIVITY_MAP.get(type);
        return (Shape) clazz.newInstance();
    }
}
