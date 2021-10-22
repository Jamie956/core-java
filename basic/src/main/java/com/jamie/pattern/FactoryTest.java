package com.jamie.pattern;

/**
 * 工厂模式
 */
public class FactoryTest {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape rectangle = shapeFactory.getShape("Rectangle");
        rectangle.draw();
        Shape rectangle2 = shapeFactory.getShape("Rectangle");
        System.out.println(rectangle == rectangle2);

        Shape square = shapeFactory.getShape("Square");
        square.draw();

        Shape circle = shapeFactory.getShape("Circle");
        circle.draw();
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

    /**
     * 工厂根据 shapeType 创建新实例
     */
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
