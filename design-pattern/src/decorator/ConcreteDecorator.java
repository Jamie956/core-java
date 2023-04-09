package decorator;

/**
 * 装饰器，代理目标类执行
 */
public class ConcreteDecorator extends AbstractDecorator {
    ConcreteDecorator(Shape target) {
        super(target);
    }

    @Override
    public void draw() {
        target.draw();
        System.out.println("do decorate...");
    }
}