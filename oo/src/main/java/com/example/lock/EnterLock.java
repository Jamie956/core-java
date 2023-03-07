package com.example.lock;

import java.util.concurrent.locks.ReentrantLock;

// 重入锁
public class EnterLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " call");
            try {
                lock.lock();
                System.out.println(name + " obtain lock");
                Thread.sleep(1000);

                // 一个线程获取某个对象的锁，可再次获取此对象的锁
                lock.lock();
                System.out.println(name + " obtain lock again");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                lock.unlock();
                System.out.println(name + " lock release");
            }
        };

        new Thread(runnable).start();
    }
}
