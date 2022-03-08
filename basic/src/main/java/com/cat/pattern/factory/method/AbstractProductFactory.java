package com.cat.pattern.factory.method;

/**
 * 产品的抽象工厂
 */
public abstract class AbstractProductFactory {
    /**
     * 由子类实现抽象方法
     * 按活动类型创建对象
     */
    protected abstract Product createProduct(String type);

    /**
     * 共同的处理逻辑
     */
    public Product Product(String type, String name) {
        System.out.println("抽象工厂做了处理");
        Product product = createProduct(type);
        product.setName(name);
        return product;
    }
}