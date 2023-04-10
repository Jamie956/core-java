package factory.simple;

public class Factory {
    // 根据 key 创建实例
    public Shape get(String key) {
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
