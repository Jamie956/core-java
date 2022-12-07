package prototype;

import java.util.Hashtable;

public class Prototype {
    private static Hashtable<String, Shape> SHAPE_MAP = new Hashtable<>();

    //克隆缓存对象
    public static Shape copy(String shapeId) {
        Shape cacheShape = SHAPE_MAP.get(shapeId);
        return (Shape)cacheShape.clone();
    }

    /**
     * 初始化缓存
     */
    public static void load() {
        Rectangle rectangle = new Rectangle();
        SHAPE_MAP.put("rectangle", rectangle);

        Circle circle = new Circle();
        SHAPE_MAP.put("circle", circle);
    }
}