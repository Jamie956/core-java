package factory.method;

/**
 * 抽象工厂
 */
public abstract class AbstractFactory {
    /**
     * 根据 type 实例对象，由子类实现
     */
    protected abstract Shape createRawInstance(String type);

    /**
     * 根据 type 实例对象：提供给工厂
     */
    public Shape createInstance(String type, String name) {
        Shape s = createRawInstance(type);
        s.setName(name);
        return s;
    }
}