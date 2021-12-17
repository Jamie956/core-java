package com.jamie.design.pattern.bridge;

/**
 * 桥接
 */
public class Client {
    public static void main(String[] args) {
        Circle redCircle = new Circle(100, 100, 10, new RedCircleDrawer());
        redCircle.draw();

        Circle greenCircle = new Circle(100, 100, 10, new GreenCircleDrawer());
        greenCircle.draw();
    }
}