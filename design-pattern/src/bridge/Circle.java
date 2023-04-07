package bridge;

public class Circle extends Shape {
    private final int r;

    protected Circle(int r, DrawAPI target) {
        super(target);
        this.r = r;
    }

    //代理执行目标对象的行为
    @Override
    void draw() {
        target.draw(r);
    }
}
