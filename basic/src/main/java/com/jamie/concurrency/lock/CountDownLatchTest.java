package com.jamie.concurrency.lock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    static void work(CountDownLatch lock) {
        System.out.println(Thread.currentThread().getName() + " 释放1把锁");
        lock.countDown();
    }

    /**
     * 部分锁释放，部分没有释放，await 会一直等待全部锁释放
     */
    /*
    private final boolean parkAndCheckInterrupt() {
        //Thread.currentThread().getName() 是主线程
        LockSupport.park(this);
        return Thread.interrupted();
    }
     */
    public static void someReleaseTest() {
        //分配3把共享锁
        CountDownLatch lock = new CountDownLatch(3);

        new Thread(() -> work(lock)).start();
        new Thread(() -> work(lock)).start();

        try {
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
        //分配3把共享锁
        CountDownLatch lock = new CountDownLatch(3);

        new Thread(() -> work(lock)).start();
        new Thread(() -> work(lock)).start();
        new Thread(() -> work(lock)).start();

        try {
            //直接返回，不需要part
            lock.await();
            System.out.println("全部锁已释放");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        someReleaseTest();
    }
}