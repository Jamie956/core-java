package com.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Class 对象的构成
public class DefinedClass {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        Class clz = getClassByInstance();

        Field[] fields = clz.getFields();
        Method[] methods = clz.getMethods();
        Annotation[] annotations = clz.getAnnotations();
        ClassLoader classLoader = clz.getClassLoader();
    }

    // 获取 Class 对象 1
    private static Class getClassByInstance() {
        DefinedClass o = new DefinedClass();
        Class<? extends DefinedClass> clz = o.getClass();
        return clz;
    }
    // 获取 Class 对象 2
    private static Class<DefinedClass> getClassByClass() {
        Class<DefinedClass> clz = DefinedClass.class;
        return clz;
    }
    // 获取 Class 对象 3
    private static Class<?> getClassbyName () throws ClassNotFoundException {
        Class<?> clz = Class.forName("com.example.ReflectClass");
        return clz;
    }
}