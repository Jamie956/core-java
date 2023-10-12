package com.lang;

import org.junit.Test;

// Class 对象的构成
public class DefinedClass {

    @Test
    public void test() throws ClassNotFoundException {
        // 通过实例对象获取 class 对象
        DefinedClass o = new DefinedClass();
        Class<? extends DefinedClass> clz1 = o.getClass();

        // 通过对象名直接获取 class 对象
        Class<DefinedClass> clz2 = DefinedClass.class;

        // 通过类路径获取 class 对象
        Class<?> clz3 = Class.forName("com.lang.DefinedClass");
    }

}