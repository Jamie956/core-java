package builder;

import java.util.ArrayList;
import java.util.List;

// 存储抽象 item list 的对象
public class Meal {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float getCost() {
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems() {
        for (Item item : items) {
            System.out.printf("%s (%s) -------- %s %n", item.name(), item.packing().pack(), item.price());
        }
    }
}