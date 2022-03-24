package factory.abstracting;

/**
 * 抽象工厂模式
 */
public class Client {
    public static void main(String[] args) {
        AbstractFactory sf = FactoryProducer.getFactory("SHAPE");
        Shape rectangle = sf.createShape("Rectangle");
        rectangle.draw();
        Shape circle = sf.createShape("Circle");
        circle.draw();


        AbstractFactory cf = FactoryProducer.getFactory("COLOR");
        Color red = cf.createColor("Red");
        red.fill();
        Color blue = cf.createColor("Blue");
        blue.fill();
    }
}
