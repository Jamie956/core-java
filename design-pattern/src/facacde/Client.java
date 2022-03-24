package facacde;

/**
 * 外观模式（Facade Pattern）
 * 向客户端提供了一个客户端可以访问系统的接口，隐藏系统的复杂性
 */
public class Client {
    public static void main(String[] args) {
        ShapeMaker s = new ShapeMaker();
        s.drawCircle();
        s.drawRectangle();
    }
}
