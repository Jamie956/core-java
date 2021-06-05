package com.jamie.pattern;

/**
 * 抽象工厂模式
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory shape = FactoryProducer.getFactory("SHAPE");
        Shape rectangle = shape.getShape("Rectangle");
        rectangle.draw();
        Shape square = shape.getShape("Square");
        square.draw();
        Shape circle = shape.getShape("Circle");
        circle.draw();

        AbstractFactory color = FactoryProducer.getFactory("COLOR");
        Color red = color.getColor("Red");
        red.fill();
        Color blue = color.getColor("Blue");
        blue.fill();
        Color green = color.getColor("Green");
        green.fill();

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

    interface Color {
        void fill();
    }

    static class Red implements Color{
        @Override
        public void fill() {
            System.out.println("fill Red");
        }
    }

    static class Green implements Color{
        @Override
        public void fill() {
            System.out.println("fill Green");
        }
    }

    static class Blue implements Color{
        @Override
        public void fill() {
            System.out.println("fill Blue");
        }
    }

    abstract static class AbstractFactory {
        public abstract Color getColor(String color);
        public abstract Shape getShape(String shape);
    }

    static class ShapeFactory extends AbstractFactory{
        @Override
        public Color getColor(String color) {
            return null;
        }

        @Override
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

    static class ColorFactory extends AbstractFactory{
        @Override
        public Color getColor(String color) {
            switch (color) {
                case "Red":
                    return new Red();
                case "Green":
                    return new Green();
                case "Blue":
                    return new Blue();
                default:
                    break;
            }
            return null;
        }

        @Override
        public Shape getShape(String shape) {
            return null;
        }
    }

    static class FactoryProducer {
        public static AbstractFactory getFactory(String choice) {
            switch (choice) {
                case "SHAPE":
                    return new ShapeFactory();
                case "COLOR":
                    return new ColorFactory();
                default:
                    break;
            }
            return null;
        }
    }

}


