package com.util_concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

// 简单自旋锁
public class SpinLock {
    /**
     * 使用Owner Thread作为同步状态
     */
    private AtomicReference<Thread> sign = new AtomicReference<>();

    /**
     * reentrant count of a thread, no need to be volatile
     */
    public void lock() throws InterruptedException {
        Thread t = Thread.currentThread();
        // 自旋
        while (!sign.compareAndSet(null, t)) {
            System.out.println(t.getName() + " spin");
            Thread.sleep(1000);
        }
    }

    public void unlock() {
        Thread t = Thread.currentThread();
        sign.compareAndSet(t, null);
    }

    public static void main(String[] args) {
        SpinLock lock = new SpinLock();

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            try {
                lock.lock();
                System.out.println(name + " obtain lock");
                for (int i = 1; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(name + " doing task " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(name + " unlock");
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}