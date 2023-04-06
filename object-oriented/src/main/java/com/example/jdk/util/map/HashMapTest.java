package com.example.jdk.util.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class HashMapTest {
    @Test
    public void cons() {
        HashMap<Object, Object> map = new HashMap<>(12, 0.5f);
    }

    @Test
    public void cons2() {
        HashMap<Object, Object> map = new HashMap<>(12);
    }

    @Test
    public void cons3() {
        HashMap<Object, Object> map = new HashMap<>();
    }

    @Test
    public void cons4() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");

        HashMap<Object, Object> map2 = new HashMap<>(map);
    }

    @Test
    public void get() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");

        map.get("k1");
    }

    @Test
    public void put() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");
    }

    @Test
    public void putAll() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");

        HashMap<Object, Object> map2 = new HashMap<>();
        map2.putAll(map);
    }

    @Test
    public void remove() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");

        map.remove("k1");
    }

    @Test
    public void clear() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");

        map.clear();
    }

    @Test
    public void containsValue() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        map.containsValue("v2");
    }

    @Test
    public void keySet() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        Set<Object> s = map.keySet();
        s.iterator();
        s.contains("k1");
        s.remove("k1");
        s.forEach(e-> System.out.println(e));
    }

    @Test
    public void values() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        map.values();
    }

    @Test
    public void entrySet() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        map.entrySet();
    }

    @Test
    public void getOrDefault() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", null);

        map.getOrDefault("k1", "0");
        map.getOrDefault("k2", "0");
    }

    @Test
    public void putIfAbsent() {
        HashMap<Object, Object> map = new HashMap<>();
        map.putIfAbsent("k1", "v1");
        map.putIfAbsent("k1", "update");
        map.putIfAbsent("k2", "v2");
    }

    @Test
    public void removekv() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");
        map.remove("k1", "v1");
    }

    @Test
    public void replace() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");
        map.replace("k1", "v1", "newv");
    }

    @Test
    public void replace2() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");
        map.replace("k1", "newb");
    }

    @Test
    public void computeIfAbsent() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");
        Object k1 = map.computeIfAbsent("k1", e -> e + "1");
        Object k2 = map.computeIfAbsent("k2", e -> e + "1");
    }

    @Test
    public void computeIfPresent() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");

        BiFunction<Object, Object, Object> bf = (k, v) -> String.format("k=%s, v=%s", k, v);
        map.computeIfPresent("k1", bf);
    }

    @Test
    public void compute() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");

        BiFunction<Object, Object, Object> bf = (k, v) -> String.format("k=%s, v=%s", k, v);
        map.compute("k1", bf);
    }

    @Test
    public void merge() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");

        BiFunction<Object, Object, Object> bf = (oldValue, newValue) -> String.format("k=%s, v=%s", oldValue, newValue);
        map.merge("k1","v1", bf);
    }

    @Test
    public void forEach() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");

        BiConsumer<Object, Object> f = (k, v) -> System.out.println(String.format("k=%s, v=%s", k, v));
        map.forEach(f);
    }

    @Test
    public void replaceAll() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");

        BiFunction<Object, Object, Object> f = (k, v) -> String.format("k=%s, v=%s", k, v);
        map.replaceAll(f);
    }

    @Test
    public void clone1() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("k1", "v1");

        map.clone();
    }
}
