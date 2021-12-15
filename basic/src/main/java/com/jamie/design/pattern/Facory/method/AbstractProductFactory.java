package com.jamie.design.pattern.Facory.method;

/**
 * 产品的抽象工厂
 */
public abstract class AbstractProductFactory {
    /**
     * 有子类实现具体的创建方法
     * 按活动类型创建对象
     */
    protected abstract Product createProduct(String activity);

    public Product Product(String activity, String name) {
        Product product = createProduct(activity);
        //对象共性处理
        product.setName(name);
        return product;
    }
}