package com.jamie.design.pattern.factory.method;

public class Client {
    public static void main(String[] args) {
        //创建具体工厂
        ProductFactory factory = new ProductFactory();
        //执行父类创建对象方法，做共性处理，再调抽象方法的重写（有子类实现），做个性处理
        Product product = factory.Product("one", "one");
        System.out.println(product.getName());
    }
}
