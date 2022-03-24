package factory.simple;

/**
 * 工厂
 */
public class Factory {
    /**
     * 根据 key 创建实例
     */
    public Shape createInstance(String key) {
        switch (key) {
            case "Square":
                return new Square();
            case "Circle":
                return new Circle();
            default:
                break;
        }
        return null;
    }
}
