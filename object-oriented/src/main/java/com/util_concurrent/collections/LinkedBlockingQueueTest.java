package com.util_concurrent.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingQueueTest {
    @Test
    public void cons1() {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>();
    }

    @Test
    public void cons2() {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(2);
    }

    @Test
    public void cons3() {
        ArrayList<String> l = new ArrayList<>();
        l.add("2");
        l.add("8");
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(l);
    }

    @Test
    public void put() throws InterruptedException {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>();
        q.put("5");
        q.put("4");
        q.put("9");
    }

    @Test
    public void offer() throws InterruptedException {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>();
        q.offer("5", 3, TimeUnit.SECONDS);
    }

    @Test
    public void offer2() {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>();
        q.offer("5");
    }

    @Test
    public void take() throws InterruptedException {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>();
        q.offer("5");
        q.offer("7");
        q.offer("0");
        String r = q.take();
    }

    @Test
    public void poll() throws InterruptedException {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>();
        q.offer("5");
        q.offer("7");
        q.offer("0");

        String a1 = q.poll(3, TimeUnit.SECONDS);
        String a2 = q.poll(3, TimeUnit.SECONDS);
        String a3 = q.poll(3, TimeUnit.SECONDS);
    }

    @Test
    public void poll2() {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(3);
        q.offer("5");
        q.offer("7");
        q.offer("0");

        String a1 = q.poll();
        String a2 = q.poll();
        String a3 = q.poll();
    }

    @Test
    public void peek() {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(3);
        q.offer("5");
        q.offer("7");
        q.offer("0");

        String a1 = q.peek();
    }

    @Test
    public void remove() {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(3);
        q.offer("5");
        q.offer("7");
        q.offer("0");

        boolean b = q.remove("7");
    }

    @Test
    public void contains() {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(3);
        q.offer("5");
        q.offer("7");
        q.offer("0");
        boolean b = q.contains("0");
    }

    @Test
    public void toArray() {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(3);
        q.offer("5");
        q.offer("7");
        q.offer("0");
        Object[] arr = q.toArray();
    }

    @Test
    public void toArray2() {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(3);
        q.offer("5");
        q.offer("7");
        q.offer("0");
        String[] arr = {"3", "7", "0"};
        Object[] r = q.toArray(arr);
    }

    @Test
    public void clear() {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(3);
        q.offer("5");
        q.offer("7");
        q.offer("0");
        q.clear();
    }

    @Test
    public void drainTo() {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(3);
        q.offer("5");
        q.offer("7");
        q.offer("0");
        ArrayList<String> l = new ArrayList<>();
        int i = q.drainTo(l, 2);
    }

    @Test
    public void iterator() {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(3);
        q.offer("5");
        q.offer("7");
        q.offer("0");
        Iterator<String> it = q.iterator();
        String a1 = it.next();
        String a2 = it.next();
        String a3 = it.next();
    }
}
