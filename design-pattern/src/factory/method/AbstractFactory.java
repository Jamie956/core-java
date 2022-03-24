package factory.method;

/**
 * 抽象工厂
 */
public abstract class AbstractFactory {
    /**
     * 根据 key 创建对象，由子类实现
     */
    protected abstract Shape createRawInstance(String type);

    /**
     * 根据 key 创建对象：先调子类实现的创建方法，再做共同逻辑处理
     */
    public Shape createInstance(String type, String name) {
        Shape s = createRawInstance(type);
        s.setName(name);
        return s;
    }
}