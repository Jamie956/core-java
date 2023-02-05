package org.example;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAspectTest {
    @Test
    public void t0() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //扫描包下面的注解
        context.scan("org.example");
        context.refresh();
        //从容器获取代理对象实例
        ProxyTarget target = context.getBean(ProxyTarget.class);
        target.hi();
    }

    @Test
    public void t1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.example");
        context.refresh();
        ProxyTarget target = context.getBean(ProxyTarget.class);
        target.hi2();
    }
}
