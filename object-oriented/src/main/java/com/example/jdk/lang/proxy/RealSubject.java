package com.example.jdk.lang.proxy;


public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("do request");
    }
}
