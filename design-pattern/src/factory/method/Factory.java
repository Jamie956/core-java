package factory.method;

/**
 * 工厂
 */
public class Factory extends AbstractFactory {

    @Override
    protected Shape createRawInstance(String key) {
        if ("circle".equalsIgnoreCase(key)) {
            return new Circle();
        } else if ("rectange".equalsIgnoreCase(key)) {
            return new Rectange();
        }
        return null;
    }
}