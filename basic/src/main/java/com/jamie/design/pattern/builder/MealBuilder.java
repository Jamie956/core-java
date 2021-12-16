package com.jamie.design.pattern.builder;

/**
 * 套餐制作子类
 */
public class MealBuilder {
    /**
     * 制作一个套餐：蔬菜汉堡+可乐
     */
    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    /**
     * 制作一个套餐：鸡肉汉堡+百事
     */
    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}