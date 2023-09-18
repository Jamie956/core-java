package com.util_map;

import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class TreeMapTest {
    @Test
    public void cons() {
        TreeMap<Object, Object> m = new TreeMap<>();
    }

    @Test
    public void cons2() {
        Comparator<Object> c = Comparator.comparingInt(e -> (int) e);
        TreeMap<Object, Object> m = new TreeMap<>(c);
    }

    @Test
    public void cons3() {
        HashMap<Object, Object> hm = new HashMap<>();
        hm.put("k1", "v1");
        hm.put("k2", "v2");
        TreeMap<Object, Object> m = new TreeMap<>(hm);
    }

    @Test
    public void cons4() {
        TreeMap<Object, Object> tm = new TreeMap<>();
        tm.put("k3", "v3");
        tm.put("k1", "v1");
        tm.put("k2", "v2");
        TreeMap<Object, Object> m = new TreeMap<>(tm);
    }

    @Test
    public void containsKey() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        boolean b = m.containsKey("k1");
    }

    @Test
    public void containsValue() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        boolean b = m.containsValue("v1");
    }

    @Test
    public void get() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        String v = m.get("k1");
    }

    @Test
    public void firstKey() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        String v = m.firstKey();
    }

    @Test
    public void lastKey() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        String v = m.lastKey();
    }

    @Test
    public void putAll() {
        TreeMap<String, String> m0 = new TreeMap<>();
        m0.put("k1", "v1");
        m0.put("k2", "v2");

        TreeMap<String, String> m = new TreeMap<>();
        m.putAll(m0);
    }

    @Test
    public void putAll2() {
        HashMap<String, String> m0 = new HashMap<>();
        m0.put("k1", "v1");
        m0.put("k2", "v2");

        TreeMap<String, String> m = new TreeMap<>();
        m.putAll(m0);
    }

    @Test
    public void put() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
    }

    @Test
    public void remove() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", "v3");

        m.remove("k2");
    }

    @Test
    public void clear() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        m.clear();
    }

    @Test
    public void clone1() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        Object clone = m.clone();
    }

    @Test
    public void firstEntry() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        Map.Entry<String, String> a = m.firstEntry();
//        a.setValue("update");
    }

    @Test
    public void lastEntry() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        Map.Entry<String, String> a = m.lastEntry();
    }

    @Test
    public void pollFirstEntry() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        Map.Entry<String, String> a = m.pollFirstEntry();
    }

    @Test
    public void pollLastEntry() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        Map.Entry<String, String> a = m.pollLastEntry();
    }

    @Test
    public void lowerEntry() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", "v3");
        m.put("k4", "v4");

        Map.Entry<String, String> a = m.lowerEntry("k3");
    }

    @Test
    public void lowerKey() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        String a = m.lowerKey("k1");
    }

    @Test
    public void floorEntry() {
        TreeMap<Integer, String> m = new TreeMap<>();
        m.put(1, "v1");
        m.put(2, "v2");
        m.put(3, "v3");
        m.put(4, "v4");
        m.put(6, "v6");

        Map.Entry<Integer, String> a = m.floorEntry(5);
    }

    @Test
    public void floorKey() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        String a = m.floorKey("k1");
    }

    @Test
    public void ceilingEntry() {
        TreeMap<Integer, String> m = new TreeMap<>();
        m.put(1, "v1");
        m.put(2, "v2");
        m.put(3, "v3");
        m.put(4, "v4");
        m.put(6, "v6");

        Map.Entry<Integer, String> a = m.ceilingEntry(5);
        Map.Entry<Integer, String> b = m.ceilingEntry(0);
        Map.Entry<Integer, String> c = m.ceilingEntry(1);
        Map.Entry<Integer, String> d = m.ceilingEntry(7);
    }

    @Test
    public void ceilingKey() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        String a = m.ceilingKey("k1");
    }

    @Test
    public void higherEntry() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        Map.Entry<String, String> a = m.higherEntry("k1");
    }

    @Test
    public void higherKey() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        String a = m.higherKey("k1");
    }

    @Test
    public void keySet() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        Set<String> s = m.keySet();
    }

    @Test
    public void navigableKeySet() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        Set<String> s = m.navigableKeySet();
    }

    @Test
    public void descendingKeySet() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        Set<String> s = m.descendingKeySet();
    }

    @Test
    public void values() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        Collection<String> values = m.values();
    }

    @Test
    public void entrySet() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        Set<Map.Entry<String, String>> s = m.entrySet();
    }

    @Test
    public void descendingMap() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");

        NavigableMap<String, String> s = m.descendingMap();
    }

    @Test
    public void subMap() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", "v3");
        m.put("k4", "v4");
        m.put("k5", "v5");

        NavigableMap<String, String> s = m.subMap("k2", true, "k4", false);
    }

    @Test
    public void headMap() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", "v3");

        NavigableMap<String, String> s = m.headMap("k2", true);
    }

    @Test
    public void tailMap() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", "v3");
        m.put("k4", "v4");

        NavigableMap<String, String> s = m.tailMap("k3", true);
    }

    @Test
    public void subMap1() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", "v3");
        m.put("k4", "v4");

        SortedMap<String, String> s = m.subMap("k2", "k4");
    }

    @Test
    public void headMap3() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", "v3");
        m.put("k4", "v4");

        SortedMap<String, String> s = m.headMap("k3");
    }

    @Test
    public void tailMap1() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", "v3");
        m.put("k4", "v4");

        SortedMap<String, String> s = m.tailMap("k3");
    }

    @Test
    public void replace() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", "v3");
        m.put("k4", "v4");

        boolean b = m.replace("k3", "v3", "update");
    }

    @Test
    public void replace1() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", "v3");
        m.put("k4", "v4");

        String a = m.replace("k3", "update");
    }

    @Test
    public void forEach() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", "v3");
        m.put("k4", "v4");

        BiConsumer<String, String> c = (k, v) -> System.out.println(k + " " + v);
        m.forEach(c);
    }

    @Test
    public void replaceAll() {
        TreeMap<String, String> m = new TreeMap<>();
        m.put("k1", "v1");
        m.put("k2", "v2");
        m.put("k3", "v3");
        m.put("k4", "v4");

        BiFunction<String, String, String> f = (k, v) -> k + v;
        m.replaceAll(f);
    }
}
