package com.cat.pattern.factory.method;

/**
 * 工厂
 */
public class ShapeFactory extends AbstractFactory {
    /**
     * 重写父类方法
     */
    @Override
    protected Shape getInstance(String type) {
        if ("circle".equalsIgnoreCase(type)) {
            return new Circle();
        } else if ("rectange".equalsIgnoreCase(type)) {
            return new Rectange();
        }
        return null;
    }
}