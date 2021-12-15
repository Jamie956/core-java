package com.jamie.design.pattern.Facory;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂 使用反射机制
 * 直接注册商品对象，添加一个Type类型方法，根据type类型返回自身相同类型的方法
 * <p>
 * 缺点：
 * 反射机制会降低程序的运行效果，在对性能要求很高的场景下应该避免这种实现
 * 反射不当是容易导致线上机器出问题的，因为我们反射创建的对象属性是被SoftReference软引用的，所以当**-XX:SoftRefLRUPolicyMSPerMB** 没有设置好的话会一直让机器CPU很高。
 */
public class SimpleFactoryReflection {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        addProductType(ProductTypeEnum.activityOne, ActivityOne.class);
        ActivityOne product = product(ProductTypeEnum.activityOne);
        System.out.println(product);
    }

    /**
     * product type to class
     */
    private static final Map<ProductTypeEnum, Class> ACTIVITY_MAP = new HashMap<>();

    /**
     * 产品类型 -- 对应class 加到map
     */
    public static void addProductType(ProductTypeEnum type, Class productClazz) {
        ACTIVITY_MAP.put(type, productClazz);
    }

    /**
     * 根据类型获取class
     * class 反射构造函数，实例化对象
     */
    public static ActivityOne product(ProductTypeEnum type) throws IllegalAccessException, InstantiationException {
        Class clazz = ACTIVITY_MAP.get(type);
        return (ActivityOne) clazz.newInstance();

    }

    public static class Product {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ActivityOne extends Product {
        private String stock;
    }

    public static class ActivityTwo extends Product {
        private String stock;
    }

    public enum ProductTypeEnum {
        activityOne, activityTwo
    }
}
