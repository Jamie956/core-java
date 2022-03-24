package builder;

/**
 * 建造者模式
 */
public class Client {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal meal = mealBuilder.prepareVegMeal();
        System.out.println("VegMeal:");
        meal.showItems();
        System.out.println("total:" + meal.getCost());

        System.out.println();
        Meal meal1 = mealBuilder.prepareNonVegMeal();
        System.out.println("VegMeal:");
        meal1.showItems();
        System.out.println("total:" + meal1.getCost());
    }
}