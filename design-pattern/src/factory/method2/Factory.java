package factory.method2;

import java.util.ArrayList;
import java.util.List;

/**
 * 模版工厂
 */
public class Factory {
    /**
     * 缓存的实例
     */
    private List<Shape> list;

    /**
     * 从缓存取实例
     */
    public Shape cache(String key) {
        for (Shape share : list) {
            //对象预先定义好了key
            if (share.getKey().equalsIgnoreCase(key)) {
                return share;
            }
        }
        return null;
    }

    /**
     * 缓存实例
     */
    public void register(Shape share) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(share);
    }
}
