package builder;

public abstract class Burger implements Item {
    /**
     * 共同特征
     */
    @Override
    public Packing packing() {
        return new Wrapper();
    }
    /**
     * 由子类实现
     */
    @Override
    public abstract float price();
}
