package com.lang;

import org.junit.Test;

public class StringBufferTest {
    @Test
    public void cons() {
        StringBuffer a = new StringBuffer();
    }

    @Test
    public void ensureCapacity() {
        StringBuffer a = new StringBuffer();
        a.ensureCapacity(16);
    }

    @Test
    public void trimToSize() {
        StringBuffer a = new StringBuffer("  123456 ");
        a.trimToSize();
    }

    @Test
    public void setLength() {
        StringBuffer a = new StringBuffer("123456");
        a.setLength(10);
    }

    @Test
    public void charAt() {
        StringBuffer a = new StringBuffer("123456");
        char r = a.charAt(2);
    }

    @Test
    public void getChars() {
        StringBuffer a = new StringBuffer("123456");
        char[] arr = new char[10];
        a.getChars(1, 3, arr, 3);
    }

    @Test
    public void append() {
        StringBuffer a = new StringBuffer("123456");
        a.append("aaa");
    }

    @Test
    public void delete() {
        StringBuffer a = new StringBuffer("123456");
        a.delete(1, 3);
    }


}
