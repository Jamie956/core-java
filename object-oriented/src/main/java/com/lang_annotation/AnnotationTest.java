package com.lang_annotation;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationTest {
    @Test
    public void classLevelAnnotationTest() {
        String name = UsingClassAnnotation.class.getAnnotation(ClassLevelAnnotation.class).name();
        String version = UsingClassAnnotation.class.getAnnotation(ClassLevelAnnotation.class).version();
        Assert.assertEquals("updated name", name);
        Assert.assertEquals("2.0.0", version);
    }

    @Test
    public void fieldLevelAnnotationTest() throws NoSuchFieldException {
        Field valField = UsingFieldAnnotation.class.getDeclaredField("val");
        String name = valField.getAnnotation(FieldLevelAnnotation.class).name();
        Assert.assertEquals("default value", name);
    }

    @Test
    public void methodLevelAnnotationTest() throws NoSuchMethodException {
        Method methodHalo = UsingMethodAnnotation.class.getMethod("halo");
        String name = methodHalo.getAnnotation(MethodLevelAnnotation.class).name();
        Assert.assertEquals("halo", name);
    }
}
