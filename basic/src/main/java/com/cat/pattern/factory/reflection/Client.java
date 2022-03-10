package com.cat.pattern.factory.reflection;

/**
 * 简单工厂（反射）：缓存Class对象，根据反射构造实例
 * 缺点：反射机制降低程序性能
 */
public class Client {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        ShapeReflectionFactory.register("rectangle", Rectangle.class);
        Shape r = ShapeReflectionFactory.getObject("rectangle");
        r.draw();
    }
}
