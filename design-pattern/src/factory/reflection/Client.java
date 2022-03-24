package factory.reflection;

/**
 * 工厂模式-反射
 */
public class Client {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Factory.register("rectangle", Rectangle.class);
        Shape s = Factory.cache("rectangle");
        s.draw();
    }
}
