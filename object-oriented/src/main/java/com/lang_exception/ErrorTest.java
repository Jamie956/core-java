package com.lang_exception;

import org.junit.Test;

public class ErrorTest {
    @Test
    public void cons() {
        Error e = new Error();
        e.printStackTrace();
    }

    @Test
    public void cons2() {
        Error e = new Error("exp ee");
        e.printStackTrace();
    }

    @Test
    public void cons3() {
        Error e = new Error("exp ee", new Error("cause aa"));
        e.printStackTrace();
    }

    @Test
    public void cons4() {
        Error e = new Error(new Error("cause aa"));
        e.printStackTrace();
    }
}
