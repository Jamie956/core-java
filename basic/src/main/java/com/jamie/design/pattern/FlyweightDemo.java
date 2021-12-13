package com.jamie.design.pattern;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * 享元模式（Flyweight Pattern）主要用于减少创建对象的数量，以减少内存占用和提高性能。这种类型的设计模式属于结构型模式，它提供了减少对象数量从而改善应用所需的对象结构的方式。
 * 从缓存读取实例
 */
public class FlyweightDemo {
    public static void main(String[] args) {
        Circle circle = (Circle) ShapeFactory.getCircle("Red");
        circle.setX(1);
        circle.setY(2);
        circle.setRadius(3);
        circle.draw();

        Circle circle2 = (Circle) ShapeFactory.getCircle("Red");
        circle2.draw();

        System.out.println(circle == circle2);
    }

    interface Shape {
        void draw();
    }

    @Setter
    @Getter
    static class Circle implements Shape {
        private String color;
        private int x;
        private int y;
        private int radius;

        public Circle(String color) {
            System.out.println("Circle 实例化");
            this.color = color;
        }

        @Override
        public void draw() {
            System.out.println("draw x=" + x + " y=" + y + " radius=" + radius);
        }
    }

    static class ShapeFactory {
        private static final HashMap<String, Shape> MAP = new HashMap<>();

        /**
         * 从缓存获取实例，如果缓存没有就创建实例，并且放入缓存
         */
        public static Shape getCircle(String color) {
            Circle circle = (Circle) MAP.get(color);
            if (circle == null) {
                System.out.println("缓存没有实例，创建新实例，并且放入缓存");
                circle = new Circle(color);
                MAP.put(color, circle);
            }
            return circle;
        }
    }
}
