package com.jamie.concurrency.collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {
    static BlockingQueue<Integer> qu = new LinkedBlockingQueue<Integer>(3);

    static void work() {
        try {
            System.out.println(Thread.currentThread().getName() + " start put");
            qu.put(1);
            System.out.println(Thread.currentThread().getName() + " end put");

            System.out.println(Thread.currentThread().getName() + " start take");
            qu.take();
            System.out.println(Thread.currentThread().getName() + " end take");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> work()).start();
        new Thread(() -> work()).start();
        new Thread(() -> work()).start();
        new Thread(() -> work()).start();
    }
}
