package factory.simple;

/**
 * 工厂模式-静态
 * https://www.runoob.com/design-pattern/factory-pattern.html
 */
public class Client {
    public static void main(String[] args) {
        Factory factory = new Factory();

        Shape square = factory.get("Square");
        square.draw();

        Shape circle = factory.get("Circle");
        circle.draw();
    }
}
