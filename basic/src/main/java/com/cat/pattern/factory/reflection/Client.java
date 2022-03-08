package com.cat.pattern.factory.reflection;

public class Client {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        ShapeReflectionFactory.register("rectangle", Rectangle.class);
        Shape r = ShapeReflectionFactory.getObject("rectangle");
        r.draw();
    }
}
