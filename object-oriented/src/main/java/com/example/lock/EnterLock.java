package com.example.lock;

import java.util.concurrent.locks.ReentrantLock;

// ReentrantLock 测试锁重入
public class EnterLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " call");
            try {
                lock.lock();
                System.out.printf("1 tread=%s, holdCount=%s%n", name, lock.getHoldCount());
                // 一个线程获取某个对象的锁，可再次获取此对象的锁
                lock.lock();
                System.out.printf("2 tread=%s, holdCount=%s%n", name, lock.getHoldCount());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                lock.unlock();
            }
        };

        new Thread(runnable).start();
    }
}
