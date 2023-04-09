package decorator;

/**
 * 装饰器模式：向一个现有对象添加新的功能，但是不改变其结构
 * https://www.runoob.com/design-pattern/decorator-pattern.html
 */
public class Client {
    public static void main(String[] args) {
        Circle circle = new Circle();
        ConcreteDecorator circleWithDecorate = new ConcreteDecorator(circle);
        circleWithDecorate.draw();
    }
}
