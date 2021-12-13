package com.jamie.design.pattern;

/**
 * 装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，同时又不改变其结构。这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。
 */
public class DecoratorDemo {
    public static void main(String[] args) {
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());

        redCircle.draw();
        redRectangle.draw();
    }

    interface Shape {
        void draw();
    }

    static class Rectangle implements Shape {
        public Rectangle() {
            System.out.println("Rectangle 实例化");
        }

        @Override
        public void draw() {
            System.out.println("画 rectangle");
        }
    }

    static class Circle implements Shape {
        public Circle() {
            System.out.println("Circle 实例化");
        }

        @Override
        public void draw() {
            System.out.println("画 Circle");
        }
    }

    abstract static class ShapeDecorator implements Shape {
        protected Shape decoratedShape;

        ShapeDecorator(Shape decoratedShape){
            System.out.println("ShapeDecorator 实例化");

            this.decoratedShape = decoratedShape;
        }

        @Override
        public void draw() {
            decoratedShape.draw();
        }
    }

    static class RedShapeDecorator extends ShapeDecorator {
        RedShapeDecorator(Shape decoratedShape) {
            super(decoratedShape);
            System.out.println("RedShapeDecorator 实例化");
        }

        @Override
        public void draw() {
            decoratedShape.draw();
            setRedBorder(decoratedShape);
        }

        private void setRedBorder(Shape decoratedShape) {
            System.out.println(decoratedShape.getClass().getSimpleName() + " 装饰一个红色的边");
        }
    }

}
