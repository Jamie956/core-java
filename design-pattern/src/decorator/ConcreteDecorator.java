package decorator;

/**
 * 装饰器做具体的装饰处理
 */
public class ConcreteDecorator extends AbstractDecorator {
    ConcreteDecorator(Shape target) {
        super(target);
    }

    /**
     * 代理目标类行使目标类的行为，可增加装饰处理
     */
    @Override
    public void draw() {
        target.draw();
        System.out.println("do decorate...");
    }

}