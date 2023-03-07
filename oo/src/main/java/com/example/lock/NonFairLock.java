package com.example.lock;

import java.util.concurrent.locks.ReentrantLock;

// 非公平锁乱序竞争
public class NonFairLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(false);

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            try {
                System.out.println(name + " call");
                lock.lock();
                System.out.println(name + " obtain lock");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
