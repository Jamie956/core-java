package facacde;

public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;

    /**
     * 实例化时把代理对象创建好
     */
    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }
}
