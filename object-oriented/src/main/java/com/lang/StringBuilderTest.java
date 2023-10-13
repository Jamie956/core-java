package com.lang;

import org.junit.Assert;
import org.junit.Test;

public class StringBuilderTest {

    @Test
    public void cons() {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder(8);
        StringBuilder sb3 = new StringBuilder("abc123");

    }

    @Test
    public void append() {
        StringBuilder sb = new StringBuilder();
        sb.append("abc123");

        Assert.assertEquals("abc123", sb.toString());
    }

    @Test
    public void append2() {
        StringBuffer a = new StringBuffer();
        a.append("abc123");
        StringBuilder b = new StringBuilder();
        b.append(a);

        Assert.assertEquals("abc123", a.toString());
        Assert.assertEquals("abc123", b.toString());
    }

    @Test
    public void append3() {
        char[] arr = {'a', 'c'};
        StringBuilder b = new StringBuilder();
        b.append(arr);

        Assert.assertEquals("ac", b.toString());
    }

    @Test
    public void append4() {
        char[] arr = {'a', 'c', 'f', 's'};
        StringBuilder b = new StringBuilder();
        b.append(arr, 1, 2);

        Assert.assertEquals("cf", b.toString());
    }

    @Test
    public void append5() {
        StringBuilder b = new StringBuilder();
        b.append(true);

        Assert.assertEquals("true", b.toString());
    }

    @Test
    public void append6() {
        StringBuilder b = new StringBuilder();
        b.append('a');

        Assert.assertEquals("a", b.toString());
    }

    @Test
    public void append7() {
        StringBuilder b = new StringBuilder();
        b.append(554);

        Assert.assertEquals("554", b.toString());
    }

    @Test
    public void delete() {
        StringBuilder b = new StringBuilder();
        b.append("123456789");
        b.delete(2, 5);

        Assert.assertEquals("126789", b.toString());
    }

    @Test
    public void deleteCharAt() {
        StringBuilder a = new StringBuilder("123456789");
        a.deleteCharAt(1);

        Assert.assertEquals("13456789", a.toString());
    }

    @Test
    public void replace() {
        StringBuilder a = new StringBuilder("123456789");
        a.replace(1, 3, "aaaaaa");

        Assert.assertEquals("1aaaaaa456789", a.toString());
    }

    @Test
    public void insert() {
        StringBuilder a = new StringBuilder("123456789");
        char[] arr = {'a', 'a', 'a', 'a', 'a'};
        a.insert(2, arr, 0, 5);

        Assert.assertEquals("12aaaaa3456789", a.toString());
    }

    @Test
    public void indexOf() {
        StringBuilder a = new StringBuilder("123456789");
        int i = a.indexOf("4");

        Assert.assertEquals(3, i);
    }

    @Test
    public void reverse() {
        StringBuilder a = new StringBuilder("123456789");
        a.reverse();

        Assert.assertEquals("987654321", a.toString());
    }


}
