package prototype;

/**
 * 原型模式（Prototype Pattern）：创建重复的对象
 */
public class Client {
    public static void main(String[] args) {
        Prototype.load();

        Shape s1 = Prototype.copy("rectangle");
        Shape s2 = Prototype.copy("rectangle");

        Shape s3 = Prototype.copy("circle");
        Shape s4 = Prototype.copy("circle");
    }
}
