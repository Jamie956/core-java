package com.example.jdk.util.collection;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;

public class ArrayDequeTest {
    @Test
    public void cons() {
        ArrayDeque<String> q = new ArrayDeque<>();
    }

    @Test
    public void cons1() {
        ArrayDeque<String> q = new ArrayDeque<>(2);
    }

    @Test
    public void cons2() {
        ArrayList<String> a = new ArrayList<>();
        a.add("1");
        a.add("5");
        ArrayDeque<String> q = new ArrayDeque<>(a);
    }

    @Test
    public void addFirst() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.addFirst("1");
        q.addFirst("2");
    }

    @Test
    public void addLast() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.addLast("1");
        q.addLast("2");
    }

    @Test
    public void addLast2() {
        ArrayDeque<String> q = new ArrayDeque<>();
        for (int i = 0; i < 17; i++) {
            //i == 15
            q.addLast("1");
        }
    }

    @Test
    public void offerFirst() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("5");

        q.offerFirst("0");
    }

    @Test
    public void offerLast() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("5");

        q.offerLast("0");
    }

    @Test
    public void removeFirst() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("5");

        String s = q.removeFirst();
    }


    @Test
    public void removeLast() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("5");

        String s = q.removeLast();
    }

    @Test
    public void pollFirst() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("5");

        String s = q.pollFirst();
    }

    @Test
    public void pollLast() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("5");

        String s = q.pollLast();
    }

    @Test
    public void getFirst() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("5");

        String s = q.getFirst();
    }

    @Test
    public void getLast() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("5");

        String s = q.getLast();
    }

    @Test
    public void peekFirst() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("5");

        String s = q.peekFirst();
    }

    @Test
    public void peekLast() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("5");

        String s = q.peekLast();
    }

    @Test
    public void removeFirstOccurrence() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");
        q.add("1");
        q.add("2");
        q.add("2");

        boolean s = q.removeFirstOccurrence("1");
    }

    @Test
    public void removeFirstOccurrence2() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("2");
        q.add("1");
        q.add("1");
        q.add("2");
        q.add("2");

        boolean s = q.removeFirstOccurrence("1");
    }

    @Test
    public void removeLastOccurrence() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");
        q.add("1");
        q.add("2");
        q.add("1");

        boolean s = q.removeLastOccurrence("1");
    }

    @Test
    public void removeLastOccurrence2() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");
        q.add("1");
        q.add("1");
        q.add("2");

        boolean s = q.removeLastOccurrence("1");
    }

    @Test
    public void add() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");
    }

    @Test
    public void offer() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        boolean b = q.offer("3");
    }

    @Test
    public void remove() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        String a = q.remove();
    }

    @Test
    public void poll() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        String a = q.poll();
    }

    @Test
    public void element() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        String a = q.element();
    }

    @Test
    public void peek() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        String a = q.peek();
    }

    @Test
    public void push() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        q.push("3");
    }

    @Test
    public void pop() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        String s = q.pop();
    }

    @Test
    public void size() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        int s = q.size();
    }

    @Test
    public void isEmpty() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        boolean s = q.isEmpty();
    }

    @Test
    public void iterator() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        Iterator<String> iterator = q.iterator();
    }

    @Test
    public void descendingIterator() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        Iterator<String> iterator = q.descendingIterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }

    @Test
    public void contains() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        boolean b = q.contains("1");
    }

    @Test
    public void remove1() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        boolean b = q.remove("1");
    }

    @Test
    public void clear() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        q.clear();
    }

    @Test
    public void toArray() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        Object[] arr = q.toArray();
    }

    @Test
    public void toArray1() {
        String[] arr = new String[]{"3", "4"};
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        Object[] r = q.toArray(arr);
    }

    @Test
    public void clone1() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        ArrayDeque<String> c = q.clone();
    }


    @Test
    public void spliterator() {
        ArrayDeque<String> q = new ArrayDeque<>();
        q.add("1");
        q.add("2");

        Spliterator<String> c = q.spliterator();
    }


}
