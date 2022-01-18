package com.jamie.concurrency.collections;

import com.jamie.concurrency.ThreadUtil;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class COWArraySet {
    public static void work(Set<String> list) {
        for (int i = 1; i < 6; i++) {
            list.add(Thread.currentThread().getName() + "  " + i % 3);
        }
    }

    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();

        ThreadUtil.execute(() -> work(set));
        ThreadUtil.execute(() -> work(set));
    }

}
