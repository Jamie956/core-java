package com.cat.pattern.flyweight;

import java.util.HashMap;

/**
 * 形状工厂
 */
public class ShapeFactory {
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
