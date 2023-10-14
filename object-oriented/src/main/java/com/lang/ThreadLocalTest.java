package com.lang;

import org.junit.Assert;
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
        Assert.assertEquals("999", a);
        tl.set("998");
        String b = tl.get();
        Assert.assertEquals("998", b);
    }

    @Test
    public void remove() {
        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("999");
        String a = tl.get();
        Assert.assertEquals("999", a);
        tl.remove();
        String b = tl.get();
        Assert.assertNull(b);
    }

}
