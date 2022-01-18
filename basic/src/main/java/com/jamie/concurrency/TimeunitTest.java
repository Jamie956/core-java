package com.jamie.concurrency;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TimeunitTest {
    /**
     * TimeUnit的 sleep方法
     */
    @Test
    public void timeunitTes() throws InterruptedException {
        System.out.println("sleep begin");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sleep end");
    }

    /**
     * timedWait 带锁方法，获取锁等待
     */
    public synchronized void work() {
        System.out.println("Begin Work");
        try {
            TimeUnit.SECONDS.timedWait(this, 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work End");
    }
}
