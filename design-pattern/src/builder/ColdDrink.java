package builder;

/**
 * 抽象类
 */
public abstract class ColdDrink implements Item {
    /**
     * 共同特征
     */
    @Override
    public Packing packing() {
        return new Bottle();
    }

    /**
     * 由子类实现
     */
    @Override
    public abstract float price();
}