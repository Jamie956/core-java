package com.example;

public class ReflectClass {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ReflectClass o = new ReflectClass();
        ReflectClass reflectClass = o.getClass().newInstance();
    }
}
