package com.example.lock;

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
        // spin 自旋
        while (!sign.compareAndSet(null, t)) {
            System.out.println(t.getName() + " spin..");
            Thread.sleep(100);
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
            System.out.println(name + " call");
            try {
                lock.lock();
                System.out.println(name + " obtain lock");
                Thread.sleep(1000);
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