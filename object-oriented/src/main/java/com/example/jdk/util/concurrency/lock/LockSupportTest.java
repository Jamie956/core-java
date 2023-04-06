package com.example.jdk.util.concurrency.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    @Test
    public void unpark() {
        LockSupport.unpark(Thread.currentThread());
    }

    @Test
    public void park() {
        Object o = new Object();
        LockSupport.park(o);
    }

    @Test
    public void park2() {
        Object o = new Object();
        LockSupport.parkNanos(o, 100000L);
    }

    @Test
    public void park3() {
        Object o = new Object();
        LockSupport.parkUntil(o, System.currentTimeMillis() + 10000);
    }

    @Test
    public void getBlocker() {
        Object o = LockSupport.getBlocker(Thread.currentThread());
    }

    @Test
    public void parkUntil() {
        LockSupport.parkUntil(System.currentTimeMillis() + 10000);
    }

    //------------------------------
    public void unparkTest() {
        Thread t1 = new Thread(() -> {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + " park");
            //断点 suspend
            LockSupport.park(t);
        });

        Thread t2 = new Thread(() -> {
            System.out.println("wait to unpark");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t1.getName() + " unpark");
            //断点 suspend
            LockSupport.unpark(t1);
        });

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        LockSupportTest t = new LockSupportTest();
        t.unparkTest();
    }
}
