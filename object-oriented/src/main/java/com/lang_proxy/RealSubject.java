package com.lang_proxy;


public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("do request");
    }
}
