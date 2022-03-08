package com.cat.pattern.flyweight;

import lombok.Getter;
import lombok.Setter;

/**
 * 实现形状接口：圆
 */
@Setter
@Getter
public class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color) {
        System.out.println("Circle 实例化");
        this.color = color;
    }

    /**
     * 实现形状接口，根据构造参数画圆
     */
    @Override
    public void draw() {
        System.out.println("draw x=" + x + " y=" + y + " radius=" + radius);
    }
}
