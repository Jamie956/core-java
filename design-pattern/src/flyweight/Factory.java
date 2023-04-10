package flyweight;

import java.util.HashMap;

public class Factory {
    // 缓存的实例
    private static final HashMap<String, Shape> MAP = new HashMap<>();

    public static Shape get(String key) {
        Shape target = MAP.get(key);
        if (target == null) {
            target = new Circle();
            MAP.put(key, target);
        }
        return target;
    }
}
