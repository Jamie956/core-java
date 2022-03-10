package com.cat.pattern.factory.simple;

/**
 * 工厂类：据key创建新实例
 */
public class Factory {
    public Shape getInstance(String key) {
        switch (key) {
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
