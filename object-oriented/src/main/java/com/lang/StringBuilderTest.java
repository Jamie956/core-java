package com.lang;

import org.junit.Test;

public class StringBuilderTest {

    @Test
    public void cons() {
        StringBuilder sb = new StringBuilder();
    }

    @Test
    public void cons1() {
        StringBuilder sb = new StringBuilder(8);
    }

    @Test
    public void cons2() {
        StringBuilder sb = new StringBuilder("abc123");
    }

    @Test
    public void append() {
        StringBuilder sb = new StringBuilder();
        sb.append("abc123");
    }

    @Test
    public void append2() {
        StringBuffer a = new StringBuffer();
        a.append("abc123");
        StringBuilder b = new StringBuilder();
        b.append(a);
    }

    @Test
    public void append3() {
        char[] arr = {'a', 'c'};
        StringBuilder b = new StringBuilder();
        b.append(arr);
    }

    @Test
    public void append4() {
        char[] arr = {'a', 'c', 'f', 's'};
        StringBuilder b = new StringBuilder();
        b.append(arr, 1, 2);
    }

    @Test
    public void append5() {
        StringBuilder b = new StringBuilder();
        b.append(true);
    }

    @Test
    public void append6() {
        StringBuilder b = new StringBuilder();
        b.append('a');
    }

    @Test
    public void append7() {
        StringBuilder b = new StringBuilder();
        b.append(554);
    }

    @Test
    public void delete() {
        StringBuilder b = new StringBuilder();
        b.append("123456789");
        b.delete(2, 5);
    }

    @Test
    public void deleteCharAt() {
        StringBuilder a = new StringBuilder("123456789");
        a.deleteCharAt(1);
    }

    @Test
    public void replace() {
        StringBuilder a = new StringBuilder("123456789");
        a.replace(1, 3, "aaaaaa");
    }

    @Test
    public void insert() {
        StringBuilder a = new StringBuilder("123456789");
        char[] arr = {'a', 'a', 'a', 'a', 'a'};
        a.insert(2, arr, 0, 5);
    }

    @Test
    public void indexOf() {
        StringBuilder a = new StringBuilder("123456789");
        int i = a.indexOf("4");
    }

    @Test
    public void reverse() {
        StringBuilder a = new StringBuilder("123456789");
        a.reverse();
    }


}
