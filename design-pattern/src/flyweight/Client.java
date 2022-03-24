package flyweight;

/**
 * 享元模式（Flyweight Pattern）：减少创建对象的数量，减少内存占用，提高性能
 */
public class Client {
    public static void main(String[] args) {
        Circle c1 = (Circle) Factory.getCircle("Red");
        Circle c2 = (Circle) Factory.getCircle("Red");
        Circle c3 = (Circle) Factory.getCircle("Black");
    }
}
