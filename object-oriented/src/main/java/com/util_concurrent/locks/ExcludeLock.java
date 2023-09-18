package com.util_concurrent.locks;

import java.util.concurrent.locks.ReentrantLock;

// ReentrantLock 测试独占锁/悲观锁
public class ExcludeLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            System.out.printf("thread=%s, before get lock%n", name);
            try {
                lock.lock();
                System.out.printf("thread=%s, get lock%n", name);

                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.printf("thread=%s, release lock%n", name);

            }
        };

        // 线程独占资源，其他线程访问这个资源时阻塞，直到资源被线程释放其他线程才能竞争资源
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
