package com.jamie.design.pattern.factory.method;

public class Client {
    public static void main(String[] args) {
        ProductFactory factory = new ProductFactory();
        Product product = factory.Product("one", "one");
        System.out.println(product.getName());
    }
}
