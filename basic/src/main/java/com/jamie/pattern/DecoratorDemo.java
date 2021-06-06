package com.jamie.pattern;

public class DecoratorDemo {
    public static void main(String[] args) {
        Circle circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());

        circle.draw();
        redCircle.draw();
        redRectangle.draw();
    }

    interface Shape {
        void draw();
    }

    static class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("draw rectangle");
        }
    }

    static class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("draw Circle");
        }
    }

    abstract static class ShapeDecorator implements Shape {
        protected Shape decoratedShape;

        ShapeDecorator(Shape decoratedShape){
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
        }

        @Override
        public void draw() {
            decoratedShape.draw();
            setRedBorder(decoratedShape);
        }

        private void setRedBorder(Shape decoratedShape) {
            System.out.println("red border");
        }
    }

}
