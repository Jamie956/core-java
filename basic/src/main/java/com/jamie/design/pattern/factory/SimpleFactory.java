package com.jamie.design.pattern.factory;

/**
 * 简单工厂模式 -- 静态工厂模式
 * 缺点：每增加一种类型都要去修改工厂方法，违背开闭原则
 * <p>
 * 工厂按类型创建对象
 * 被创建的对象 实现共同接口/继承同一个类
 */
public class SimpleFactory {
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

    static class ShapeFactory {
        public Shape getShape(String shapeType) {
            //根据 shapeType 创建新实例
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
