package bridge;

public class DrawerA implements Drawer {
    @Override
    public void draw(int x) {
        System.out.println("DrawerA x=" + x);
    }
}