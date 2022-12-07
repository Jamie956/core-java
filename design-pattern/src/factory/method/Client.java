package factory.method;

/**
 * 工厂方法模式
 */
public class Client {
    public static void main(String[] args) {
        Factory f = new Factory();
        Shape s = f.createInstance("circle", "instance name");
        System.out.println(s.getName());
    }
}