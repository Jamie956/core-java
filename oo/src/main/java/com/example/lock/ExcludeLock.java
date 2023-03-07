package com.example.lock;

import java.util.concurrent.locks.ReentrantLock;

// 独占锁/悲观锁
public class ExcludeLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " call");
            try {
                lock.lock();
                System.out.println(name + " obtain lock");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(name + " lock release");
            }
        };

        // 线程独占资源，其他线程访问这个资源时阻塞，直到资源被释放
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
