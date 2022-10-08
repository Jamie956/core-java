package com.example;

import net.sf.cglib.proxy.Enhancer;

public class CGlibProxy {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new MyMethodInterceptor());

        RealSubject proxyDog = (RealSubject) enhancer.create();
        proxyDog.request();
    }
}