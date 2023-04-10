package prototype;

import java.util.Hashtable;

public class Prototype {
    private static Hashtable<String, Shape> SHAPE_MAP = new Hashtable<>();

    //克隆缓存对象
    public static Shape copy(String shapeId) {
        Shape cacheShape = SHAPE_MAP.get(shapeId);
        return (Shape)cacheShape.clone();
    }

    public static void load() {
        SHAPE_MAP.put("rectangle", new Rectangle());
        SHAPE_MAP.put("circle", new Circle());
    }
}