package com.jamie.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    /**
     * 实例化一把读锁 和 写锁
     */
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void task(Lock rwLock) {
        //下一行打断点，选择 Thread，可以看到每条线程的断点
        rwLock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.unlock();
        }
    }

    /**
     * 锁共享，无序
     */
    public static void readLockTest() {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

        new Thread(() -> task(readLock)).start();
        new Thread(() -> task(readLock)).start();
        new Thread(() -> task(readLock)).start();
    }

    /**
     * 锁独占，有序
     */
    public static void writeLockTest() {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        new Thread(() -> task(writeLock)).start();
        new Thread(() -> task(writeLock)).start();
        new Thread(() -> task(writeLock)).start();
    }

    public static void main(String[] args) {
        readLockTest();
    }
}