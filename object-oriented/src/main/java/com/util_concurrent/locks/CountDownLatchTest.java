package com.util_concurrent.locks;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    @Test
    public void cons() {
        CountDownLatch c = new CountDownLatch(2);
    }

    @Test
    public void await() throws InterruptedException {
        CountDownLatch c = new CountDownLatch(2);
        c.await();
    }

    @Test
    public void awaitTime() throws InterruptedException {
        CountDownLatch c = new CountDownLatch(2);
        c.await(3, TimeUnit.SECONDS);
        c.await(3, TimeUnit.SECONDS);
        c.await(3, TimeUnit.SECONDS);
    }

    @Test
    public void countDown() {
        CountDownLatch c = new CountDownLatch(2);
        c.countDown();
        c.countDown();
        c.countDown();
    }

    /**
     * 部分锁释放，部分没有释放，await 会一直等待全部锁释放
     */
    public static void someReleaseTest() {
        //分配3把共享锁
        //Thread Suspend
        CountDownLatch lock = new CountDownLatch(3);

        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + " 释放1把锁");
            //Thread Suspend
            lock.countDown();
        };

        new Thread(r).start();
        new Thread(r).start();

        try {
            //Thread Suspend
            lock.await();
            System.out.println("全部锁已释放");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分配的3把锁全部释放，main 线程继续运行
     */
    public static void allReleaseTest() {
        CountDownLatch lock = new CountDownLatch(3);

        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + " 释放1把锁");
            //Thread Suspend
            lock.countDown();
        };

        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();

        try {
            //Thread Suspend
            lock.await();
            System.out.println("全部锁已释放");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        someReleaseTest();
        allReleaseTest();
    }
}