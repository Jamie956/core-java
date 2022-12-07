package bridge;

public abstract class Shape {
    //目标对象
    protected Drawer target;

    protected Shape(Drawer target) {
        this.target = target;
    }

    //目标对象行为
    abstract void draw();
}
