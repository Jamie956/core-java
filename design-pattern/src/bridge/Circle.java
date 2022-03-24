package bridge;

public class Circle extends Shape {
    private int x;

    protected Circle(int x, Drawer target) {
        super(target);
        this.x = x;
    }

    @Override
    void draw() {
        //执行代理类的行为
        target.draw(x);
    }
}
