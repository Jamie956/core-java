package decorator;

/**
 * 抽象装饰器，与代理目标类实现同一个接口，具有相同的行为
 */
public abstract class AbstractDecorator implements Shape {
    protected Shape target;

    /**
     * 构造时指定代理的目标对象
     */
    AbstractDecorator(Shape target) {
        this.target = target;
    }

    /**
     * 代理目标类行使目标类的行为
     */
    @Override
    public void draw() {
        target.draw();
    }
}