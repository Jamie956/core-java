package com.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectClassMethodField {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        // 获取 Class 对象的方式
        ReflectClassMethodField o = new ReflectClassMethodField();
        Class<? extends ReflectClassMethodField> c1 = o.getClass();
        Class<ReflectClassMethodField> c2 = ReflectClassMethodField.class;
        Class<?> c3 = Class.forName("com.example.ReflectClass");

        Field[] fields = c1.getFields();
        Method[] methods = c1.getMethods();
        Annotation[] annotations = c1.getAnnotations();
        ClassLoader classLoader = c1.getClassLoader();

        Field field = c1.getDeclaredField("");
        field.setAccessible(true);
        field.get(o);
        field.set(o, "value");
    }
}
