package flyweight;

/**
 * 享元模式（Flyweight Pattern）：减少创建对象的数量，减少内存占用，提高性能
 * https://www.runoob.com/design-pattern/flyweight-pattern.html
 */
public class Client {
    public static void main(String[] args) {
        Shape c1 = Factory.get("Red");
        Shape c2 = Factory.get("Red");
        Shape c3 = Factory.get("Black");

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }
}
