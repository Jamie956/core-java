package com.util_collection;

import org.junit.Test;

import java.util.*;

public class TreeSetTest {
    @Test
    public void cons() {
        TreeSet<Integer> s = new TreeSet<>();
    }

    @Test
    public void cons2() {
        Comparator<Object> c = Comparator.comparingInt(e -> (int) e);
        TreeSet<Integer> s = new TreeSet<>(c);
    }

    @Test
    public void cons3() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        TreeSet<Integer> s = new TreeSet<>(list);
    }

    @Test
    public void iterator() {
        TreeSet<Integer> s = new TreeSet<>();
        Iterator<Integer> it = s.iterator();
    }

    @Test
    public void descendingIterator() {
        TreeSet<Integer> s = new TreeSet<>();
        Iterator<Integer> it = s.descendingIterator();
    }

    @Test
    public void descendingSet() {
        TreeSet<Integer> s = new TreeSet<>();
        NavigableSet<Integer> nav = s.descendingSet();
    }

    @Test
    public void contains() {
        TreeSet<Integer> s = new TreeSet<>();
        s.add(2);
        boolean b = s.contains(2);
    }

    @Test
    public void add() {
        TreeSet<Integer> s = new TreeSet<>();
        s.add(2);
    }

    @Test
    public void addAll() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        TreeSet<Integer> s = new TreeSet<>();
        boolean b = s.addAll(list);
    }

    @Test
    public void subSet() {
        TreeSet<Integer> s = new TreeSet<>();
        for (int i = 0; i < 6; i++) {
            s.add(i);
        }
        NavigableSet<Integer> integers = s.subSet(3, true, 5, true);
    }









































}
