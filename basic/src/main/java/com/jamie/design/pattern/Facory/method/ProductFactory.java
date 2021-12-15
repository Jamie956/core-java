package com.jamie.design.pattern.Facory.method;

/**
 * 具体工厂，继承抽象工厂
 */
public class ProductFactory extends AbstractProductFactory {
    /**
     * 重写父类的创建方法
     */
    @Override
    protected Product createProduct(String activity) {
        if (EnumActivityType.activityOne.getName().equalsIgnoreCase(activity)) {
            //个性处理
            return new OneProduct();
        } else if (EnumActivityType.activityTwo.getName().equalsIgnoreCase(activity)) {
            return new TwoProduct();
        }
        return null;
    }
}