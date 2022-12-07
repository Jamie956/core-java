package facacde;

/**
 * 外观模式（Facade Pattern）
 * https://www.runoob.com/design-pattern/facade-pattern.html
 */
public class Client {
    public static void main(String[] args) {
        ShapeMaker s = new ShapeMaker();
        s.drawCircle();
        s.drawRectangle();
    }
}
