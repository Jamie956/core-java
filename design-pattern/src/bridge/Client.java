package bridge;

/**
 * 桥接
 */
public class Client {
    public static void main(String[] args) {
        //client 决定桥接哪个类行使行为
        Circle d1 = new Circle(100, new DrawerA());
        d1.draw();

        Circle d2 = new Circle(101, new DrawerB());
        d2.draw();
    }
}