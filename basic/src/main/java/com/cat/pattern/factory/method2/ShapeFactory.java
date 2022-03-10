package com.cat.pattern.factory.method2;

import java.util.ArrayList;
import java.util.List;

/**
 * 模版工厂
 */
public class ShapeFactory {
    /**
     * 缓存对象
     */
    private List<Shape> list;

    /**
     * 遍历缓存，根据key获取实例
     */
    public Shape getInstance(String key) {
        for (Shape share : list) {
            if (share.getKey().equalsIgnoreCase(key)) {
                return share;
            }
        }
        return null;
    }

    /**
     * 缓存实例
     */
    public void register(Shape share){
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(share);
    }
}
