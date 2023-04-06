package com.example.exception;

/*
RuntimeException:
IllegalArgumentException
NullPointerException
ArrayIndexOutOfBoundsException
NoSuchFieldException
 */
public class UncheckedException {
    public void foo() {


        // Exception class: IllegalArgumentException -> RuntimeException -> Exception -> Throwable

        // happened in runtime with unknown condition, parameters and ...

        throw new IllegalArgumentException();
    }
}
