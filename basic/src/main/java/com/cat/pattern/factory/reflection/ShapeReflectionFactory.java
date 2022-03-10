package com.cat.pattern.factory.reflection;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂（反射）
 */
public class ShapeReflectionFactory {
    /**
     * 缓存 key-Class对象
     */
    private static final Map<String, Class<?>> ACTIVITY_MAP = new HashMap<>();

    /**
     * 注册 Class对象
     */
    public static void register(String type, Class<?> productClazz) {
        ACTIVITY_MAP.put(type, productClazz);
    }

    /**
     * 根据注册key，反射构造Class的实例
     */
    public static Shape getObject(String type) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = ACTIVITY_MAP.get(type);
        return (Shape) clazz.newInstance();
    }
}
