package factory.abstracting;

/**
 * 创建工厂的工厂
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {
        switch (choice) {
            case "SHAPE":
                return new ShapeFactory();
            case "COLOR":
                return new ColorFactory();
            default:
                break;
        }
        throw new RuntimeException("对象不存在");
    }
}