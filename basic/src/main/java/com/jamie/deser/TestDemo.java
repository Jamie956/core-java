package com.jamie.deser;

public class TestDemo {
    public static void main(String[] args) {
        new StudentSerializable("tim", 20);

        new StudentDeserializable();
    }
}