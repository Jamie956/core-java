package com.jamie.design.pattern.bridge;

/**
 * 实现画图接口：画绿色圆形类
 */
public class GreenCircleDrawer implements DrawAPI {
    /**
     * 画绿色圆形，我可以画，但是要告诉我数据
     */
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing green circle radius=" + radius + ", x=" + x + ", y=" + y);
    }
}