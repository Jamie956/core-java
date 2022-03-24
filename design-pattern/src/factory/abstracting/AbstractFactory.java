package factory.abstracting;

/**
 * 抽象工厂
 */
public abstract class AbstractFactory {
    /**
     * 创建实例，由子类实现
     */
    public abstract Color createColor(String color);
    /**
     * 创建实例，由子类实现
     */
    public abstract Shape createShape(String shape);
}