package factory.abstracting;

/**
 * 工厂
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color createColor(String color) {
        return null;
    }

    /**
     * 创建实例
     */
    @Override
    public Shape createShape(String shapeType) {
        switch (shapeType) {
            case "Rectangle":
                return new Rectangle();
            case "Circle":
                return new Circle();
            default:
                break;
        }
        return null;
    }
}
