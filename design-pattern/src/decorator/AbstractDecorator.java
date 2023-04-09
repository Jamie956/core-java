package decorator;

/**
 * 抽象装饰器，与目标对象实现相同的接口
 */
public abstract class AbstractDecorator implements Shape {
    protected Shape target;

    AbstractDecorator(Shape target) {
        this.target = target;
    }

    @Override
    public void draw() {
        target.draw();
    }
}