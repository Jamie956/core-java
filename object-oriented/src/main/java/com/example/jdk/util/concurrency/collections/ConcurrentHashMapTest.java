package com.example.jdk.util.concurrency.collections;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        put2();
    }

    @Test
    public void cons() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    }

    @Test
    public void cons2() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(12);
    }

    @Test
    public void cons3() {
        Map<String, String> m = new HashMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(m);
    }

    @Test
    public void cons4() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(12, 0.5f);
    }

    @Test
    public void cons5() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(12, 0.5f, 13);
    }

    @Test
    public void size() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        int size = map.size();
    }

    @Test
    public void isEmpty() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        boolean b = map.isEmpty();
    }

    @Test
    public void get() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        String v2 = map.get("k2");
    }

    @Test
    public void containsKey() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        boolean b = map.containsKey("k1");
    }

    @Test
    public void containsValue() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        boolean b = map.containsValue("v2");
    }

    @Test
    public void put() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
    }

    //多线程 put冲突
    public static void put2() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        Thread t1 = new Thread(() -> {
            //Suspend
            map.put("k1", "v1");
        });
        Thread t2 = new Thread(() -> {
            //Suspend
            map.put("k1", "v1");
        });
        t1.start();
        t2.start();
    }

    @Test
    public void putAll() {
        Map<String, String> m = new HashMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putAll(m);
    }

    @Test
    public void remove() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.remove("k1");
    }

    @Test
    public void clear() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(2);
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.clear();
    }

    @Test
    public void keySet() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        ConcurrentHashMap.KeySetView<String, String> s = map.keySet();
        Iterator<String> iterator = s.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }

    @Test
    public void values() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        Collection<String> c = map.values();
        Object[] objects = c.toArray();
    }

    @Test
    public void entrySet() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        Set<Map.Entry<String, String>> en = map.entrySet();
    }

    @Test
    public void hashCode1() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        int hs = map.hashCode();
    }

    @Test
    public void toString1() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        String s = map.toString();
    }

    @Test
    public void equals11() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        ConcurrentHashMap<String, String> map2 = new ConcurrentHashMap<>();
        map2.put("k1", "v1");
        map2.put("k2", "v2");

        boolean eq = map.equals(map2);
    }

    @Test
    public void putIfAbsent() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        String s = map.putIfAbsent("k2", "v3");
    }

    @Test
    public void remove1() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        boolean b = map.remove("k1", "v1");
    }

    @Test
    public void replace() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        boolean b = map.replace("k1", "v1", "up");
    }

    @Test
    public void replace2() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        String r = map.replace("k1", "up");
    }


}