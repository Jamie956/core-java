package com.example.exception;

/*
常见 RuntimeException
IllegalArgumentException
NullPointerException
ArrayIndexOutOfBoundsException
NoSuchFieldException
 */
public class UncheckedException {
    public static void main(String[] args) {
        // throw 用在方法体内
        throw new IllegalArgumentException();
    }
}
