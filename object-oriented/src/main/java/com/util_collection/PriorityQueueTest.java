package com.util_collection;

import org.junit.Test;

import java.util.*;

public class PriorityQueueTest {
    @Test
    public void cons() {
        PriorityQueue<Object> q = new PriorityQueue<>();
    }

    @Test
    public void cons1() {
        Comparator<Object> c = Comparator.comparingInt(e -> (int) e);
        PriorityQueue<Object> q = new PriorityQueue<>(c);
        q.add(2);
        q.add(1);
        q.add(9);
        q.add(5);
        q.add(11);
    }

    @Test
    public void cons2() {
        Comparator<Object> c = Comparator.comparingInt(e -> (int) e);
        PriorityQueue<Object> q = new PriorityQueue<>(7, c);
    }

    @Test
    public void cons3() {
        List<String> l = new ArrayList<>();
        l.add("8");
        l.add("4");
        l.add("7");
        l.add("1");
        l.add("6");
        l.add("5");
        l.add("9");
        l.add("0");
        PriorityQueue<Object> q = new PriorityQueue<>(l);
    }

    @Test
    public void cons4() {
        PriorityQueue<Object> q0 = new PriorityQueue<>();
        q0.add("1");
        q0.add("2");
        PriorityQueue<Object> q = new PriorityQueue<>(q0);
    }

    @Test
    public void cons5() {
        TreeSet<String> s = new TreeSet<>();
        s.add("1");
        s.add("2");
        PriorityQueue<Object> q = new PriorityQueue<>(s);
    }

    @Test
    public void add() {
        PriorityQueue<Object> q = new PriorityQueue<>();
        q.add("1");
        q.add("2");
    }

    @Test
    public void offer() {
        PriorityQueue<Object> q = new PriorityQueue<>();
        q.offer("1");
        q.offer("2");
    }

    @Test
    public void peek() {
        PriorityQueue<Object> q = new PriorityQueue<>();
        q.add("1");
        q.add("2");

        Object peek = q.peek();
    }

    @Test
    public void remove() {
        PriorityQueue<Object> q = new PriorityQueue<>();
        q.add("1");
        q.add("2");

        boolean b = q.remove("2");
    }

    @Test
    public void contains() {
        PriorityQueue<Object> q = new PriorityQueue<>();
        q.add("1");
        q.add("2");

        boolean b = q.contains("2");
    }

    @Test
    public void toArray() {
        PriorityQueue<Object> q = new PriorityQueue<>();
        q.add("1");
        q.add("2");

        Object[] objects = q.toArray();
    }

    @Test
    public void toArray2() {
        Object[] os = new Object[]{"7", "8", "9"};
        PriorityQueue<Object> q = new PriorityQueue<>();
        q.add("1");
        q.add("2");
        q.add("3");

        Object[] objects = q.toArray(os);
    }

    @Test
    public void iterator() {
        PriorityQueue<Object> q = new PriorityQueue<>();
        q.add("1");
        q.add("2");
        q.add("3");
        Iterator<Object> iterator = q.iterator();
    }

    @Test
    public void size() {
        PriorityQueue<Object> q = new PriorityQueue<>();
        q.add("1");
        q.add("2");

        int s = q.size();
    }

    @Test
    public void clear() {
        PriorityQueue<Object> q = new PriorityQueue<>();
        q.add("1");
        q.add("2");

        q.clear();
    }

    @Test
    public void poll() {
        PriorityQueue<Object> q = new PriorityQueue<>();
        q.add("1");
        q.add("2");

        Object o = q.poll();
    }

    @Test
    public void comparator() {
        PriorityQueue<Object> q = new PriorityQueue<>();
        q.add("1");
        q.add("2");

        Comparator<? super Object> c = q.comparator();
    }


}
