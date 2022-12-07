package facacde;

public class ShapeMaker {
    private final Shape circle;
    private final Shape rectangle;

    /**
     * 构造时创建代理对象
     */
    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
    }

    //代理执行对象的行为
    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }
}
