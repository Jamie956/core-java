package com.util_concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 测试读写锁，多线程可以同时获取读锁，写锁只能被线程独占
public class ReadWriteLock {
    public static void main(String[] args) {
        //Suspend Thread
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        Runnable readTask = () -> {
            //Suspend Thread
            ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
            //Suspend Thread
            readLock.lock();
            try {
                for (int i = 1; i < 11; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " read " + i + "s");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        };

        Runnable writeTask = () -> {
            ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
            //Suspend Thread
            writeLock.lock();
            try {
                for (int i = 1; i < 11; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " write " + i + "s");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        };

        new Thread(readTask).start();
        new Thread(readTask).start();
        new Thread(readTask).start();

        new Thread(writeTask).start();
        new Thread(writeTask).start();
        new Thread(writeTask).start();
    }
}
