package com.jamie.pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Hashtable;


/**
 * 原型模式（Prototype Pattern）是用于创建重复的对象，同时又能保证性能。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 * 从缓存克隆实例
 */
public class PrototypeDemo {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape shape = ShapeCache.getShape("1");
        System.out.println(shape.toString());
        shape.draw();
        Shape copyShape = ShapeCache.getShape("1");
        System.out.println(copyShape.toString());
        //克隆的新对象
        System.out.println(shape == copyShape);

        Shape shape2 = ShapeCache.getShape("2");
        System.out.println(shape2.toString());
        Shape shape3 = ShapeCache.getShape("3");
        System.out.println(shape3.toString());
    }

    @Setter
    @Getter
    @ToString
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
            System.out.println("Rectangle 实例化");
        }
        @Override
        void draw() {
            System.out.println("draw Rectangle");
        }
    }

    static class Square extends Shape {
        public Square () {
            type = "Square";
            System.out.println("Square 实例化");
        }
        @Override
        void draw() {
            System.out.println("draw Square");
        }
    }

    static class Circle extends Shape {
        public Circle () {
            type = "Circle";
            System.out.println("Circle 实例化");
        }
        @Override
        void draw() {
            System.out.println("draw Circle");
        }
    }

    static class ShapeCache {
        private static Hashtable<String, Shape> SHAPE_MAP = new Hashtable<>();

        /**
         * 根据id 获取缓存克隆对象
         */
        public static Shape getShape(String shapeId) {
            Shape cacheShape = SHAPE_MAP.get(shapeId);
            return (Shape)cacheShape.clone();
        }

        /**
         * 查询数据库缓存到SHAPE_MAP
         */
        public static void loadCache() {
            System.out.println("查询数据库缓存到SHAPE_MAP");
            Rectangle rectangle = new Rectangle();
            rectangle.setId("1");
            SHAPE_MAP.put(rectangle.getId(), rectangle);

            Square square = new Square();
            square.setId("2");
            SHAPE_MAP.put(square.getId(), square);

            Circle circle = new Circle();
            circle.setId("3");
            SHAPE_MAP.put(circle.getId(), circle);
        }
    }
}
