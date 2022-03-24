package flyweight;

import java.util.HashMap;

/**
 * 工厂
 */
public class Factory {
    /**
     * 缓存的实例
     */
    private static final HashMap<String, Shape> MAP = new HashMap<>();

    public static Shape getCircle(String key) {
        Shape s = MAP.get(key);
        if (s == null) {
            s = new Circle(key);
            MAP.put(key, s);
        }
        return s;
    }
}
