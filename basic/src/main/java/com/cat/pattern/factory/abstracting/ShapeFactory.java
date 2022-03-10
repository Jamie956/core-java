package com.cat.pattern.factory.abstracting;

/**
 * 工厂
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }

    /**
     * 创建实例
     */
    @Override
    public Shape getShape(String shapeType) {
        switch (shapeType) {
            case "Rectangle":
                return new Rectangle();
            case "Circle":
                return new Circle();
            default:
                break;
        }
        return null;
    }
}
