package com.jamie.pattern;

import java.util.ArrayList;
import java.util.List;

public class BuilderTest {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal meal = mealBuilder.prepareVegMeal();
        meal.showItems();
        System.out.println("cost="+meal.getCost());

        System.out.println("================");

        Meal meal1 = mealBuilder.prepareNonVegMeal();
        meal1.showItems();
        System.out.println("cost="+meal1.getCost());
    }

    interface Item {
        String name();
        Packing packing();
        float price();
    }

    interface Packing {
        String pack();
    }

    static class Wrapper implements Packing {
        @Override
        public String pack() {
            return "Wrapper";
        }
    }

    static class Bottle implements Packing {
        @Override
        public String pack() {
            return "Bottle";
        }
    }

    abstract static class Burger implements Item {
        @Override
        public Packing packing() {
            return new Wrapper();
        }

        @Override
        public abstract float price();
    }

    abstract static class ColdDrink implements Item {
        @Override
        public Packing packing() {
            return new Bottle();
        }

        @Override
        public abstract float price();
    }

    static class VegBurger extends Burger {
        @Override
        public String name() {
            return "Veg Burger";
        }

        @Override
        public float price() {
            return 25.0f;
        }
    }

    static class ChickenBurger extends Burger {
        @Override
        public String name() {
            return "Chicken Burger";
        }

        @Override
        public float price() {
            return 50.0f;
        }
    }

    static class Coke extends ColdDrink {
        @Override
        public String name() {
            return "Coke";
        }

        @Override
        public float price() {
            return 30.0f;
        }
    }

    static class Pepsi extends ColdDrink {
        @Override
        public String name() {
            return "Pepsi";
        }

        @Override
        public float price() {
            return 35.0f;
        }
    }

    static class Meal {
        private List<Item> items = new ArrayList<>();

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
                System.out.println("Item="+item.name());
                System.out.println("Packing="+item.packing().pack());
                System.out.println("Price="+item.price());
            }
        }
    }

    static class MealBuilder {
        public Meal prepareVegMeal() {
            Meal meal = new Meal();
            meal.addItem(new VegBurger());
            meal.addItem(new Coke());
            return meal;
        }

        public Meal prepareNonVegMeal() {
            Meal meal = new Meal();
            meal.addItem(new ChickenBurger());
            meal.addItem(new Pepsi());
            return meal;
        }
    }

}
