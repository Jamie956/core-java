package com.cat.design.pattern.bridge;

/**
 * 定义画图接口
 */
interface DrawAPI {
    /**
     * 根据弧度、x、y 画图，具体实现由实现类实现
     */
    void drawCircle(int radius, int x, int y);
}
