package com.lang;

import org.junit.Test;

import java.util.function.Supplier;

public class ThreadLocalTest {
    @Test
    public void withInitial() {
        Supplier<String> s = () -> "1gt";
        ThreadLocal<String> tl = ThreadLocal.withInitial(s);
    }

    @Test
    public void cons() {
        ThreadLocal<String> tl = new ThreadLocal<>();
    }

    @Test
    public void get() {
        ThreadLocal<String> tl = new ThreadLocal<>();
        String s = tl.get();
    }

    @Test
    public void set() {
        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("999");
        String a = tl.get();
        tl.set("998");
        String b = tl.get();
    }

    @Test
    public void remove() {
        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("999");
        String a = tl.get();
        tl.remove();
        String b = tl.get();
    }

}
