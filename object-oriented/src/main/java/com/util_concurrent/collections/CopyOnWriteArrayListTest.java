package com.util_concurrent.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class CopyOnWriteArrayListTest {
    @Test
    public void cons() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
    }

    @Test
    public void cons2() {
        HashSet<Integer> s = new HashSet<>();
        s.add(2);
        s.add(6);
        s.add(7);
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>(s);
    }

    @Test
    public void cons3() {
        CopyOnWriteArrayList<Integer> s = new CopyOnWriteArrayList<>();
        s.add(2);
        s.add(6);
        s.add(7);
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>(s);
    }

    @Test
    public void cons4() {
        Integer[] arr = {4, 7, 9};
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>(arr);
    }

    @Test
    public void size() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(2);
        a.add(6);
        a.add(7);
        int i = a.size();
    }

    @Test
    public void contains() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(2);
        a.add(6);
        a.add(7);
        boolean b = a.contains(6);
    }

    @Test
    public void indexOf() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(2);
        a.add(6);
        a.add(7);
        int i = a.indexOf(6);
    }

    @Test
    public void indexOf2() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(2);
        a.add(1);
        a.add(6);
        a.add(7);
        int i = a.indexOf(6, 1);
    }

    @Test
    public void lastIndexOf() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(2);
        a.add(1);
        a.add(6);
        a.add(6);
        a.add(5);
        int i = a.lastIndexOf(6);
    }

    @Test
    public void lastIndexOf2() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(2);
        a.add(1);
        a.add(6);
        a.add(6);
        a.add(5);
        int i = a.lastIndexOf(6, 2);
    }

    @Test
    public void clone1() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(2);
        a.add(1);
        a.add(6);
        Object clone = a.clone();
    }

    @Test
    public void toArray() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(2);
        a.add(1);
        a.add(6);
        Object[] arr = a.toArray();
    }

    @Test
    public void toArray2() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(2);
        a.add(1);
        a.add(6);
        Integer[] arr = new Integer[4];
        Object[] r = a.toArray(arr);
    }

    @Test
    public void get() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(2);
        a.add(1);
        a.add(6);
        Integer i = a.get(2);
    }

    @Test
    public void set() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(2);
        a.add(1);
        Integer i = a.set(1, 9);
    }

    @Test
    public void add() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(2);
        a.add(1);
    }

    @Test
    public void add2() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(2);
        a.add(1);
        a.add(1, 9);
    }

    @Test
    public void remove() {
        CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();
        a.add("2");
        a.add("1");
        a.add("5");
        String v = a.remove(1);
    }

    @Test
    public void remove2() {
        CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();
        a.add("2");
        a.add("1");
        a.add("5");
        boolean b = a.remove("1");
    }

    @Test
    public void addIfAbsent() {
        CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();
        a.add("2");
        a.add("1");
        a.add("5");
        boolean b = a.addIfAbsent("1");
        boolean c = a.addIfAbsent("7");
    }

    @Test
    public void containsAll() {
        CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();
        a.add("2");
        a.add("1");
        a.add("5");

        ArrayList<String> l = new ArrayList<>();
        l.add("2");
        l.add("1");
        l.add("5");
        l.add("7");

        boolean b = a.containsAll(l);
    }

    @Test
    public void removeAll() {
        CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();
        a.add("2");
        a.add("1");
        a.add("5");

        ArrayList<String> l = new ArrayList<>();
        l.add("2");
        l.add("5");
        l.add("7");

        boolean b = a.removeAll(l);
    }

    @Test
    public void retainAll() {
        CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();
        a.add("2");
        a.add("1");
        a.add("5");

        ArrayList<String> l = new ArrayList<>();
        l.add("2");
        l.add("5");
        l.add("7");

        boolean b = a.retainAll(l);
    }

    @Test
    public void addAllAbsent() {
        CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();
        a.add("2");
        a.add("1");
        a.add("5");

        ArrayList<String> l = new ArrayList<>();
        l.add("2");
        l.add("5");
        l.add("7");
        l.add("7");
        l.add("6");

        int b = a.addAllAbsent(l);
    }

    @Test
    public void clear() {
        CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();
        a.add("2");
        a.add("1");
        a.add("5");
        a.clear();
    }

    @Test
    public void addAll() {
        CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();
        ArrayList<String> l = new ArrayList<>();
        l.add("2");
        l.add("5");
        l.add("7");
        boolean b = a.addAll(l);
    }

    @Test
    public void addAll2() {
        CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();
        a.add("0");
        a.add("1");
        a.add("2");

        ArrayList<String> l = new ArrayList<>();
        l.add("9");
        l.add("5");
        l.add("7");
        boolean b = a.addAll(1, l);
    }

    @Test
    public void forEach() {
        CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();
        a.add("0");
        a.add("1");
        a.add("2");
        Consumer<String> c = e -> System.out.println(e);
        a.forEach(c);
    }

    @Test
    public void removeIf() {
        CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();
        a.add("0");
        a.add("1");
        a.add("2");
        Predicate<String> p = e -> "2".equals(e);
        boolean b = a.removeIf(p);
    }

    @Test
    public void replaceAll() {
        CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();
        a.add("0");
        a.add("1");
        a.add("2");
        UnaryOperator<String> u = e -> e + ";";
        a.replaceAll(u);
    }

    @Test
    public void sort() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(5);
        a.add(1);
        a.add(8);
        a.add(2);
        Comparator<Object> c = Comparator.comparingInt(e -> (int) e);
        a.sort(c);
    }

    @Test
    public void toString1() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(5);
        a.add(1);
        a.add(8);

        String s = a.toString();
    }

    @Test
    public void equals1() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(5);
        a.add(1);
        a.add(8);
        CopyOnWriteArrayList<Integer> b = new CopyOnWriteArrayList<>();
        b.add(5);
        b.add(1);
        b.add(8);
//        b.add(8);
        boolean c = a.equals(b);
    }

    @Test
    public void hashCode1() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(5);
        a.add(1);
        a.add(8);
        int hc = a.hashCode();
    }

    @Test
    public void iterator() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(5);
        a.add(1);
        a.add(8);
        Iterator<Integer> it = a.iterator();
        Integer b = it.next();
        Integer c = it.next();
        Integer d = it.next();
    }

    @Test
    public void listIterator() {
        CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList<>();
        a.add(5);
        a.add(1);
        a.add(8);
        Iterator<Integer> it = a.listIterator(1);
        Integer b = it.next();
        Integer c = it.next();
    }

}