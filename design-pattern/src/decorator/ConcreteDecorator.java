package decorator;

/**
 * 装饰器 is Shape, 代理目标类执行它的行为
 */
public class ConcreteDecorator extends AbstractDecorator {
    ConcreteDecorator(Shape target) {
        super(target);
    }

    //装饰
    @Override
    public void draw() {
        target.draw();
        System.out.println("do decorate...");
    }
}