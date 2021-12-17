package com.jamie.design.pattern.decorator;

/**
 * 实现形状接口的抽象类
 */
public abstract class ShapeAbstractDecorator implements Shape {
    protected Shape decoratedShape;

    /**
     * 构造入参形状接口的实现类
     */
    ShapeAbstractDecorator(Shape decoratedShape){
        System.out.println("抽象装饰器实例化");
        this.decoratedShape = decoratedShape;
    }

    /**
     * 实现画方法，能够画画
     */
    @Override
    public void draw() {
        decoratedShape.draw();
    }
}