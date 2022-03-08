package com.cat.pattern.prototype;

import java.util.Hashtable;

/**
 * 形状缓存的克隆
 */
public class ShapeCache {
    private static Hashtable<String, Shape> SHAPE_MAP = new Hashtable<>();

    /**
     * 克隆缓存的指定对象
     */
    public static Shape getShape(String shapeId) {
        Shape cacheShape = SHAPE_MAP.get(shapeId);
        return (Shape)cacheShape.clone();
    }

    /**
     * 初始化缓存
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