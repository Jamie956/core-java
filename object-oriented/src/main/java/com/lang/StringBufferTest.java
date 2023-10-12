package com.lang;

import org.junit.Assert;
import org.junit.Test;

public class StringBufferTest {

    @Test
    public void ensureCapacity() {
        StringBuffer a = new StringBuffer();
        a.ensureCapacity(16);
        Assert.assertEquals(16, a.capacity());
    }

    @Test
    public void trimToSize() {
        StringBuffer a = new StringBuffer(16);
        a.append("  123456 ");

        Assert.assertEquals(16, a.capacity());
        a.trimToSize();
        Assert.assertEquals(9, a.capacity());
    }

    @Test
    public void setLength() {
        StringBuffer a = new StringBuffer("123456");

        Assert.assertEquals("123456", a.toString());
        a.setLength(10);
        Assert.assertEquals("123456\u0000\u0000\u0000\u0000", a.toString());
    }

    @Test
    public void charAt() {
        StringBuffer a = new StringBuffer("97");
        char c = a.charAt(1);

        Assert.assertEquals('7', c);
    }

    @Test
    public void getChars() {
        StringBuffer a = new StringBuffer("123456");
        char[] buf = new char[10];
        a.getChars(1, 3, buf, 3);

        Assert.assertEquals('2', buf[3]);
        Assert.assertEquals('3', buf[4]);
    }

    @Test
    public void append() {
        StringBuffer a = new StringBuffer("123456");
        a.append("aaa");

        Assert.assertEquals("123456aaa", a.toString());
    }

    @Test
    public void delete() {
        StringBuffer a = new StringBuffer("123456");
        a.delete(1, 3);

        Assert.assertEquals("1456", a.toString());
    }
}