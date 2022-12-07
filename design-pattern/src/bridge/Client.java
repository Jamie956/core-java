package bridge;

/**
 * 桥接
 * https://www.runoob.com/design-pattern/bridge-pattern.html
 */
public class Client {
    public static void main(String[] args) {
        //Drawer 的行为 draw 交给 Circle 执行
        Circle d1 = new Circle(100, new DrawerA());
        d1.draw();

        Circle d2 = new Circle(101, new DrawerB());
        d2.draw();
    }
}