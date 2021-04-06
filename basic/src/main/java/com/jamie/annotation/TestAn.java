package com.jamie.annotation;

import org.junit.Test;

public class TestAn {
    /**
     * 获取注解的值
     */
    @Test
    public void annotationTest() {
        MyAnnotation anno = TestAnno.class.getAnnotation(MyAnnotation.class);
        System.out.println(anno.value());
    }
}

@MyAnnotation(value = "this is value")
class TestAnno {
}