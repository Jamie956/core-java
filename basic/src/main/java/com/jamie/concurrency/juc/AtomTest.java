package com.jamie.concurrency.juc;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomTest {
    /**
     * atom
     */
    @Test
    public void atom() throws InterruptedException {
        AtomicInteger sharedValue = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    sharedValue.incrementAndGet();
                }
            }).start();
        }

        Thread.sleep(3000);
        System.out.println(sharedValue.get());
    }
}
