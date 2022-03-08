package com.cat.pattern.factory.simple;

/**
 * 工厂类
 */
public class ShapeFactory {
    public Shape getShape(String shapeType) {
        //根据key创建新实例
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
