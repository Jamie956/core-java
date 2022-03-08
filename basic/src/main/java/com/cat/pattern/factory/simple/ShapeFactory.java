package com.cat.pattern.factory.simple;

/**
 * 工厂类，按类型创建实例
 */
public class ShapeFactory {
    public Shape getShape(String shapeType) {
        //根据 shapeType 创建新实例
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
