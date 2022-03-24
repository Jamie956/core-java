package bridge;

public abstract class Shape {
    /**
     * 代理的目标类
     */
    protected Drawer target;

    protected Shape(Drawer target) {
        this.target = target;
    }

    abstract void draw();
}
