package com.jamie.pattern;

import lombok.Getter;
import lombok.Setter;

import java.util.Hashtable;

public class PrototypeDemo {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape shape = ShapeCache.getShape("1");
        System.out.println("shape="+shape.getType());
        Shape shape1 = ShapeCache.getShape("1");

        System.out.println(shape == shape1);

        Shape shape2 = ShapeCache.getShape("2");
        System.out.println("shape2="+shape2.getType());

        Shape shape3 = ShapeCache.getShape("3");
        System.out.println("shape3="+shape3.getType());

    }

    @Setter
    @Getter
    abstract static class Shape implements Cloneable {
        private String id;
        protected String type;
        abstract void draw();

        @Override
        public Object clone() {
            Object clone = null;
            try {
                clone = super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return clone;
        }
    }

    static class Rectangle extends Shape {
        public Rectangle () {
           type = "Rectangle";
        }
        @Override
        void draw() {
            System.out.println("draw Rectangle");
        }
    }

    static class Square extends Shape {
        public Square () {
            type = "Square";
        }
        @Override
        void draw() {
            System.out.println("draw Square");
        }
    }

    static class Circle extends Shape {
        public Circle () {
            type = "Circle";
        }
        @Override
        void draw() {
            System.out.println("draw Circle");
        }
    }

    static class ShapeCache {
        private static Hashtable<String, Shape> shapeMap = new Hashtable<>();

        public static Shape getShape(String shapeId) {
            Shape cacheShape = shapeMap.get(shapeId);
            return (Shape)cacheShape.clone();
        }

        public static void loadCache() {
            Rectangle rectangle = new Rectangle();
            rectangle.setId("1");
            shapeMap.put(rectangle.getId(), rectangle);

            Square square = new Square();
            square.setId("2");
            shapeMap.put(square.getId(), square);

            Circle circle = new Circle();
            circle.setId("3");
            shapeMap.put(circle.getId(), circle);

        }
    }
}
