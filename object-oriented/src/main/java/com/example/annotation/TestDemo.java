package com.example.annotation;

import org.junit.Assert;
import org.junit.Test;

public class TestDemo {
    @Test
    public void test1() {
        String value = UsingAnno.class.getAnnotation(TypeRuntimeAnnotation.class).value();
        Assert.assertEquals("annotation test value", value);
    }

}
