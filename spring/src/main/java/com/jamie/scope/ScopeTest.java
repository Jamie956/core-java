package com.jamie.scope;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScopeTest {
    @Test
    public void scopeTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.jamie.scope");
        context.refresh();
        System.out.println(context.getBean(Window.class) == context.getBean(Window.class));
        context.close();
    }
}