package com.cat.pattern.factory.method;

/**
 * 抽象工厂
 */
public abstract class AbstractShapeFactory {
    /**
     * 按类型创建对象，由子类实现抽象方法
     */
    protected abstract Shape createShape(String type);

    /**
     * 在调子类实现的方法后，父类做共同逻辑处理
     */
    public Shape shape(String type, String name) {
        Shape s = createShape(type);
        s.setName(name);
        return s;
    }
}