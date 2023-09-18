package com.util_concurrent.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetTest {
    @Test
    public void cons() {
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
    }

    @Test
    public void cons2() {
        ArrayList<String> l = new ArrayList<>();
        l.add("1");
        l.add("5");
        l.add("0");

        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>(l);
    }

    @Test
    public void equals() {
        CopyOnWriteArraySet<String> a = new CopyOnWriteArraySet<>();
        a.add("1");
        a.add("5");
        a.add("0");
        CopyOnWriteArraySet<String> b = new CopyOnWriteArraySet<>();
        b.add("1");
        b.add("5");
        b.add("0");
        boolean r = a.equals(b);
    }

    @Test
    public void equals2() {
        CopyOnWriteArraySet<String> a = new CopyOnWriteArraySet<>();
        a.add("1");
        a.add("5");
        a.add("0");
        CopyOnWriteArraySet<String> b = new CopyOnWriteArraySet<>();
        b.add("1");
        b.add("5");
        b.add("0");
        b.add("8");
        boolean r = a.equals(b);
    }

    @Test
    public void equals3() {
        CopyOnWriteArraySet<String> a = new CopyOnWriteArraySet<>();
        a.add("1");
        a.add("5");
        a.add("0");
        a.add("8");
        CopyOnWriteArraySet<String> b = new CopyOnWriteArraySet<>();
        b.add("1");
        b.add("5");
        b.add("0");
        boolean r = a.equals(b);
    }
}
