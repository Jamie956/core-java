package com.lang_exception;

import org.junit.Test;

public class ExceptionTest {
    @Test
    public void cons() {
        Exception e = new Exception();
        e.printStackTrace();
    }

    @Test
    public void cons2() {
        Exception e = new Exception("exp ee");
        e.printStackTrace();
    }

    @Test
    public void cons3() {
        Exception e = new Exception("exp ee", new Exception("cause aa"));
        e.printStackTrace();
    }

    @Test
    public void cons4() {
        Exception e = new Exception(new Exception("cause aa"));
        e.printStackTrace();
    }

}
