package com.cat.pattern.factory.method;

/**
 * 工厂方法模式
 */
public class Client {
    public static void main(String[] args) {
        ProductFactory factory = new ProductFactory();
        Product product = factory.Product("one", "one");
        System.out.println(product.getName());
    }
}
