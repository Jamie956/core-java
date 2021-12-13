package com.jamie.design.pattern;

/**
 * 外观模式（Facade Pattern）隐藏系统的复杂性，并向客户端提供了一个客户端可以访问系统的接口。这种类型的设计模式属于结构型模式，它向现有的系统添加一个接口，来隐藏系统的复杂性。
 */
public class FacadeDemo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();

        ShapeMaker shapeMaker1 = new ShapeMaker();
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
            System.out.println("Draw Rectangle");
        }
    }

    static class Square implements Shape {
        public Square() {
            System.out.println("Square 实例化");
        }

        @Override
        public void draw() {
            System.out.println("Draw Square");
        }
    }

    static class Circle implements Shape {
        public Circle() {
            System.out.println("Circle 实例化");
        }

        @Override
        public void draw() {
            System.out.println("Draw Circle");
        }
    }

    static class ShapeMaker {
        private Shape circle;
        private Shape rectangle;
        private Shape square;

        public ShapeMaker() {
            System.out.println("ShapeMaker 实例化");
            circle = new Circle();
            rectangle = new Rectangle();
            square = new Square();
        }

        /**
         * 隐藏细节
         */
        public void drawCircle() {
            circle.draw();
        }

        public void drawRectangle() {
            rectangle.draw();
        }

        public void drawSquare() {
            square.draw();
        }
    }

}
