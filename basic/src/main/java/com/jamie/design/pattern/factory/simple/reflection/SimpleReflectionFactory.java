package com.jamie.design.pattern.factory.simple.reflection;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂 使用反射机制
 * 缓存对象class，根据class反射构造实例
 * <p>
 * 缺点：
 * 反射机制会降低程序的运行效果，在对性能要求很高的场景下应该避免这种实现
 */
public class SimpleReflectionFactory {
    /**
     * 缓存对象class
     */
    private static final Map<ProductTypeEnum, Class> ACTIVITY_MAP = new HashMap<>();

    /**
     * 对象class加到map
     */
    public static void register(ProductTypeEnum type, Class productClazz) {
        ACTIVITY_MAP.put(type, productClazz);
    }

    /**
     * class反射构造实例
     */
    public static ProductOne getObject(ProductTypeEnum type) throws IllegalAccessException, InstantiationException {
        Class clazz = ACTIVITY_MAP.get(type);
        return (ProductOne) clazz.newInstance();
    }
}
