package com.util_collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListTest {
    @Test
    public void linkedListTestCons() {
        LinkedList<String> linkedList = new LinkedList<>();
    }

    @Test
    public void linkedListTestCons2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        LinkedList<Integer> linkedList = new LinkedList<>(list);
    }

    @Test
    public void getFirstTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.getFirst();
    }

    @Test
    public void getLastTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.getLast();
    }

    @Test
    public void removeFirstTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.removeFirst();
    }

    @Test
    public void removeLastTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.removeLast();
    }

    @Test
    public void addFirstTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.addFirst(9);
    }

    @Test
    public void addLastTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.addLast(7);
    }

    @Test
    public void containsTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.contains(7);
    }

    @Test
    public void addTest() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
    }

    @Test
    public void removeElementTest() {
        LinkedList<String> l = new LinkedList<>();
        l.add("1");
        l.add("2");
        l.add("3");
        l.remove("2");
    }

    @Test
    public void addAllTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(7);

        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.addAll(list);
    }

    @Test
    public void addAllTest2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(7);

        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.addAll(1, list);
    }

    @Test
    public void clearTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.clear();
    }

    @Test
    public void getTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.get(1);
    }

    @Test
    public void setTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.set(1, 9);
    }

    @Test
    public void addTestByIndex() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        l.add(1, 9);
    }

    @Test
    public void removeByIndexTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        l.remove(1);
    }

    @Test
    public void indexOfTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        l.indexOf(2);
    }

    @Test
    public void lastIndexOfTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);
        l.add(3);

        l.lastIndexOf(2);
    }

    @Test
    public void peekTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);

        l.peek();
    }

    @Test
    public void elementTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);

        l.element();
    }

    @Test
    public void pollTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);

        l.poll();
    }

    @Test
    public void removeTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);

        l.remove();
    }

    @Test
    public void offerTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);

        l.offer(3);
    }

    @Test
    public void offerFirstTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);

        l.offerFirst(3);
    }

    @Test
    public void offerLastTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);

        l.offerLast(3);
    }

    @Test
    public void peekFirst() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);

        l.peekFirst();
    }

    @Test
    public void peekLast() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);

        l.peekLast();
    }

    @Test
    public void pollFirst() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);

        l.pollFirst();
    }

    @Test
    public void pollLast() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);

        l.pollLast();
    }

    @Test
    public void push() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);

        l.push(3);
    }

    @Test
    public void pop() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);

        l.pop();
    }

    @Test
    public void removeFirstOccurrence() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);
        l.add(2);

        l.removeFirstOccurrence(2);
    }

    @Test
    public void removeLastOccurrence() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);
        l.add(2);

        l.removeLastOccurrence(2);
    }

    @Test
    public void listIterator() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);

        l.listIterator(2);
    }

    @Test
    public void descendingIterator() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);

        l.descendingIterator();
    }

    @Test
    public void cloneTest() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);

        Object clone = l.clone();
    }

    @Test
    public void toArray() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);

        l.toArray();
    }

    @Test
    public void toArrayArr() {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(2);

        Integer[] integers = l.toArray(new Integer[]{6, 7, 9});
    }

}
