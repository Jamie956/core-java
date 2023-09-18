package com.util_concurrent.locks;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// 共享锁，多个线程可以同时获取锁
public class ShareLock {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        Runnable runnable = () -> {
            try {
                semaphore.acquire();
                for (int i = 1; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " read " + i + "s");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
