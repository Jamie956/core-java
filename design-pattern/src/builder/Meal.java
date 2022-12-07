package builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造出的成品，每个成品由多个单元组成，存储在一个List
 */
public class Meal {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }


    //计算总结
    public float getCost() {
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    //打印套餐信息
    public void showItems() {
        for (Item item : items) {
            System.out.println(String.format("%s (%s) -------- %s ", item.name(), item.packing().pack(), item.price()));
        }
    }
}