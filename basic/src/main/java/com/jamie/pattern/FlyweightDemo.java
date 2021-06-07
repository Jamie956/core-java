package com.jamie.pattern;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class FlyweightDemo {
    public static void main(String[] args) {
        Circle circle = (Circle) ShapeFactory.getCircle("Red");
        circle.setX(1);
        circle.setY(2);
        circle.setRadius(3);
        circle.draw();

        Circle circle2 = (Circle) ShapeFactory.getCircle("Red");
        circle2.draw();
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
            this.color = color;
        }

        @Override
        public void draw() {

            System.out.println("draw x=" + x + " y=" + y + " radius=" + radius);
        }
    }

    static class ShapeFactory {
        private static final HashMap<String, Shape> circleMap = new HashMap<>();

        public static Shape getCircle(String color) {
            Circle circle = (Circle) circleMap.get(color);
            if (circle == null) {
                 circle = new Circle(color);
                 circleMap.put(color, circle);
                System.out.println("create");
            }
            return circle;
        }
    }
}
