package factory.method2;

/**
 * 工厂方法模式
 */
public class Client {
    public static void main(String[] args) {
        Factory f = new Factory();
        f.register(new Circle());
        f.register(new Rectangle());

        Shape c = f.cache("key-circle");
        c.process("a");

        Shape r = f.cache("key-rectangle");
        r.process("b");
    }
}
