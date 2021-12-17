package com.jamie.design.pattern.bridge;

/**
 * 形状抽象类的子类：圆形
 */
public class Circle extends Shape {
    private int x, y, radius;

    /**
     * 构造圆形
     * 入参画图数据、画图类
     */
    protected Circle(int x, int y, int radius, DrawAPI drawApi) {
        super(drawApi);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * 执行画图类的画图操作，我也可以画，但是不是直接自己画，但是我提供数据
     */
    @Override
    void draw() {
        drawApi.drawCircle(radius, x, y);
    }
}
