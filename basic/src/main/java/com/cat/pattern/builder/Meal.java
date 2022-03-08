package com.cat.pattern.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 套餐组装父类
 */
public class Meal {
    private final List<Item> items = new ArrayList<>();

    /**
     * 添加一个食品
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * 计算总价格
     */
    public float getCost() {
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    /**
     * 打印套餐信息
     */
    public void showItems() {
        for (Item item : items) {
            System.out.println(String.format("%s (%s) -------- %s ", item.name(), item.packing().pack(), item.price()));
        }
    }
}