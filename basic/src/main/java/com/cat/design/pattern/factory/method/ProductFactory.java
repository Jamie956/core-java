package com.cat.design.pattern.factory.method;

/**
 * 继承抽象工厂的工厂
 */
public class ProductFactory extends AbstractProductFactory {
    /**
     * 重写父类的创建方法
     */
    @Override
    protected Product createProduct(String type) {
        System.out.println("具体工厂创建对象");
        if (EnumProductType.productOne.getName().equalsIgnoreCase(type)) {
            return new OneProduct();
        } else if (EnumProductType.productTwo.getName().equalsIgnoreCase(type)) {
            return new TwoProduct();
        }
        return null;
    }
}