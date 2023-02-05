package org.example;

import org.springframework.stereotype.Component;

/**
 * 被代理的对象
 */
@Component
public class ProxyTarget {
    public void hi() {
        System.out.println("hi invoke");
    }

    @CustomTest
    public void hi2() {
        System.out.println("hi2 invoke");
    }
}
