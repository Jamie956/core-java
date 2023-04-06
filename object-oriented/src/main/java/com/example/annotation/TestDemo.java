package com.example.annotation;

public class TestDemo {
    public static void main(String[] args) {
        String value = UsingAnnotation.class.getAnnotation(DefinedAnnotation.class).value();
        System.out.println(value);
    }
}
