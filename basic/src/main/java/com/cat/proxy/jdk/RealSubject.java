package com.cat.proxy.jdk;


public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("do request");
    }
}
