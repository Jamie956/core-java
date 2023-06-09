package com.example.annotation;

import org.junit.Assert;
import org.junit.Test;

public class AnnotationTest {
    @Test
    public void classLevelAnnotationTest() {
        String name = UsingClassAnnotation.class.getAnnotation(ClassLevelAnnotation.class).name();
        String version = UsingClassAnnotation.class.getAnnotation(ClassLevelAnnotation.class).version();
        Assert.assertEquals("updated name", name);
        Assert.assertEquals("2.0.0", version);
    }

}
