package com.util_collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class LinkedHashSetTest {
    @Test
    public void cons() {
        LinkedHashSet<String> s = new LinkedHashSet<>(7, 0.74f);
    }

    @Test
    public void cons2() {
        LinkedHashSet<String> s = new LinkedHashSet<>(7);
    }

    @Test
    public void cons3() {
        LinkedHashSet<String> s = new LinkedHashSet<>();
    }

    @Test
    public void cons4() {
        ArrayList<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        LinkedHashSet<String> s = new LinkedHashSet<>(a);
    }
}
