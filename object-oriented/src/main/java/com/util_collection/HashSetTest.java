package com.util_collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class HashSetTest {
    @Test
    public void cons() {
        HashSet<Object> set = new HashSet<>();
    }

    @Test
    public void cons2() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        HashSet<Object> set = new HashSet<>(list);
    }

    @Test
    public void cons3() {
        HashSet<Object> set = new HashSet<>(6, 0.75f);
    }

    @Test
    public void cons4() {
        HashSet<Object> set = new HashSet<>(7);
    }

    @Test
    public void iterator() {
        HashSet<Object> set = new HashSet<>();
        set.add("1");
        set.add("2");

        Iterator<Object> iterator = set.iterator();
        System.out.println(iterator.next());
    }

    @Test
    public void size() {
        HashSet<Object> set = new HashSet<>();
        set.add("1");
        set.add("2");

        int size = set.size();
    }

    @Test
    public void isEmpty() {
        HashSet<Object> set = new HashSet<>();
        set.add("1");
        set.add("2");

        boolean v = set.isEmpty();
    }

    @Test
    public void contains() {
        HashSet<Object> set = new HashSet<>();
        set.add("1");
        set.add("2");

        boolean b = set.contains("1");
    }

    @Test
    public void add() {
        HashSet<Object> set = new HashSet<>();
        set.add("1");
        set.add("2");

        boolean b = set.add("3");
    }

    @Test
    public void remove() {
        HashSet<Object> set = new HashSet<>();
        set.add("1");
        set.add("2");

        boolean r = set.remove("1");
    }

    @Test
    public void clear() {
        HashSet<Object> set = new HashSet<>();
        set.add("1");
        set.add("2");

        set.clear();
    }

    @Test
    public void clone1() {
        HashSet<Object> set = new HashSet<>();
        set.add("1");
        set.add("2");

        set.clone();
    }

}
