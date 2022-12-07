package bridge;

public class Circle extends Shape {
    private int x;

    protected Circle(int x, Drawer target) {
        super(target);
        this.x = x;
    }

    //代理执行目标对象的行为
    @Override
    void draw() {
        target.draw(x);
    }
}
