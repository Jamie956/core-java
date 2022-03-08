package com.cat.pattern.factory.abstracting;

/**
 * 具体工厂：颜色
 */
public class ColorFactory extends AbstractFactory {
    /**
     * 创建颜色实例
     */
    @Override
    public Color getColor(String color) {
        switch (color) {
            case "Red":
                return new Red();
            case "Green":
                return new Green();
            case "Blue":
                return new Blue();
            default:
                break;
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}