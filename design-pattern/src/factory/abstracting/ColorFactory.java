package factory.abstracting;

/**
 * 工厂
 */
public class ColorFactory extends AbstractFactory {
    /**
     * 创建实例
     */
    @Override
    public Color createColor(String color) {
        switch (color) {
            case "Red":
                return new Red();
            case "Blue":
                return new Blue();
            default:
                break;
        }
        return null;
    }

    @Override
    public Shape createShape(String shape) {
        return null;
    }
}