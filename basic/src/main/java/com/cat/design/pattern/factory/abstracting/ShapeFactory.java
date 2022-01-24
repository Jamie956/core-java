package com.cat.design.pattern.factory.abstracting;

/**
 * 具体工厂：形状
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }

    /**
     * 创建形状实例
     */
    @Override
    public Shape getShape(String shapeType) {
        switch (shapeType) {
            case "Rectangle":
                return new Rectangle();
            case "Square":
                return new Square();
            case "Circle":
                return new Circle();
            default:
                break;
        }
        return null;
    }
}
