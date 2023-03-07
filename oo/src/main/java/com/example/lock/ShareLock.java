package com.example.lock;

import java.util.concurrent.Semaphore;

// 共享锁
public class ShareLock {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " call");
            try {
                semaphore.acquire();
                System.out.println(name + " obtain lock");
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println(name + " release lock");
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
