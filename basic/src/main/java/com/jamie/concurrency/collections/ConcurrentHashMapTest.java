package com.jamie.concurrency.collections;


import com.jamie.concurrency.ThreadUtil;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    public static void work(ConcurrentHashMap<String, String> map) {
        map.put("k1",  "k1");

    }

    public static void main(String[] args) {
        //ConcurrentHashMap()
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        ThreadUtil.execute(() -> work(map));
        ThreadUtil.execute(() -> work(map));
    }
}