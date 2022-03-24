package builder;

/**
 * 组成成品的单元
 */
interface Item {
    /**
     * 单元属性
     */
    String name();
    /**
     * 单元行为
     */
    Packing packing();
    /**
     * 单元属性
     */
    float price();
}
