package bridge;

public class DrawerB implements Drawer {
    @Override
    public void draw(int x) {
        System.out.println("DrawerB x=" + x);
    }
}
