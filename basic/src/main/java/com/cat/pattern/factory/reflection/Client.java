package com.cat.pattern.factory.reflection;

public class Client {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        SimpleReflectionFactory.register(ProductTypeEnum.productOne, ProductOne.class);
        ProductOne product = SimpleReflectionFactory.getObject(ProductTypeEnum.productOne);
        System.out.println(product);
    }
}
