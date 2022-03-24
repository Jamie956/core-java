package com.cat;

import org.springframework.stereotype.Component;

/**
 * AOP 被代理的对象
 * @author zjm
 */
@Component
public class ProxyTarget {
    public void hi() {
        System.out.println("hi invoke");
    }

    @CheckUser
    public void greeting() {
        System.out.println("greeting invoke");
    }

}
