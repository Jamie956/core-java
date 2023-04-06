package com.example.jdk.util.concurrency.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueTest {

    @Test
    public void cons1() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(5);
    }

    @Test
    public void cons2() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(5, true);
    }

    @Test
    public void cons3() {
        ArrayList<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(5, true, a);
    }

    @Test
    public void add() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        boolean a1 = q.add("2");
        boolean a2 = q.add("3");
        boolean a3 = q.add("4");
//        boolean a4 = q.add("5");
    }

    @Test
    public void offer() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        boolean a1 = q.offer("2");
    }

    @Test
    public void put() throws InterruptedException {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.put("2");
    }

    @Test
    public void offer2() throws InterruptedException {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        boolean b = q.offer("2", 3, TimeUnit.SECONDS);
    }

    @Test
    public void poll() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        q.add("2");
        String r = q.poll();
    }

    @Test
    public void take() throws InterruptedException {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        q.add("2");
        String r = q.take();
    }

    @Test
    public void poll1() throws InterruptedException {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        q.add("2");
        String r = q.poll(3, TimeUnit.SECONDS);
    }

    @Test
    public void peek() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        q.add("2");
        String a = q.peek();
    }

    @Test
    public void size() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        q.add("2");
        int a = q.size();
    }

    @Test
    public void remainingCapacity() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        int a = q.remainingCapacity();
    }

    @Test
    public void remove() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        q.add("5");
        q.add("7");
        boolean a = q.remove("5");
    }

    @Test
    public void contains() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        q.add("5");
        q.add("7");
        boolean b = q.contains("5");
    }

    @Test
    public void toArray() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        q.add("5");
        q.add("7");
        Object[] arr = q.toArray();
    }

    @Test
    public void toArray2() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        q.add("5");
        q.add("7");
        String[] arr = {"7", "3", "8"};
        Object[] r = q.toArray(arr);
    }

    @Test
    public void toString1() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        q.add("5");
        q.add("7");
        String s = q.toString();
    }

    @Test
    public void clear() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        q.add("5");
        q.add("7");
        q.clear();
    }

    @Test
    public void drainTo() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        q.add("5");
        q.add("7");

        ArrayList<String> l = new ArrayList<>();
        l.add("1");
        l.add("2");
        l.add("3");
        int i = q.drainTo(l);
    }

    @Test
    public void iterator() {
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(3);
        q.add("1");
        q.add("5");
        q.add("7");
        Iterator<String> it = q.iterator();
        String a1 = it.next();
        String a2 = it.next();
        String a3 = it.next();
    }
}
