package com.cat.design.pattern.decorator;

/**
 * 装饰器抽象类的子类
 */
public class RedShapeDecorator extends ShapeAbstractDecorator {
    /**
     * 构造函数，先调父类
     */
    RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
        System.out.println("子类装饰器实例化");
    }

    /**
     * 重写画画
     */
    @Override
    public void draw() {
        decoratedShape.draw();
        //装饰处理
        System.out.println("装饰一个红色的边");
    }

}