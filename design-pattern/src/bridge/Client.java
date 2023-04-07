package bridge;

// 桥接
// https://www.runoob.com/design-pattern/bridge-pattern.html
public class Client {
    public static void main(String[] args) {
        new Circle(100, new RedCircle()).draw();
        new Circle(101, new RedCircle()).draw();
        new Circle(100, new GreenCircle()).draw();
        new Circle(101, new GreenCircle()).draw();
    }
}