package com.jamie.pattern;

/**
 * 工厂模式
 */
public class FactoryTest {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape rectangle = shapeFactory.getShape("Rectangle");
        rectangle.draw();

        Shape square = shapeFactory.getShape("Square");
        square.draw();

        Shape circle = shapeFactory.getShape("Circle");
        circle.draw();
    }

    interface Shape {
        void draw();
    }

    static class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("Draw Rectangle");
        }
    }

    static class Square implements Shape {
        @Override
        public void draw() {
            System.out.println("Draw Square");
        }
    }

    static class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("Draw Circle");
        }
    }

    static class ShapeFactory {
        public Shape getShape(String shapeType) {
            switch (shapeType) {
                case "Rectangle":
                    return new Rectangle();
                case "Square":
                    return new Square();
                case "Circle":
                    return new Circle();
                default:
                    break;
            }
            return null;
        }
    }
}
