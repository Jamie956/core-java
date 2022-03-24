package builder;

/**
 * 子类定义属性
 */
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }
    @Override
    public float price() {
        return 30.0f;
    }
}
