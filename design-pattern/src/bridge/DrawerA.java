package bridge;

public class DrawerA implements Drawer {
    //行为
    @Override
    public void draw(int x) {
        System.out.println("DrawerA x=" + x);
    }
}