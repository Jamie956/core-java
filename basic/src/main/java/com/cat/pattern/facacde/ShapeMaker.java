package com.cat.pattern.facacde;

/**
 * 形状创建者
 */
public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    /**
     * 构造时把各个形状实现类创建好
     */
    public ShapeMaker() {
        System.out.println("ShapeMaker 实例化");
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    /**
     * 调构造函数时创建的形状的画画
     */
    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawSquare() {
        square.draw();
    }
}
