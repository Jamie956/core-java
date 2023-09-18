package com.util_concurrent.locks;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    @Test
    public void cons() {
        Semaphore lock = new Semaphore(3);
    }

    @Test
    public void cons2() {
        Semaphore lock = new Semaphore(3, true);
    }

    @Test
    public void acquire() throws InterruptedException {
        Semaphore lock = new Semaphore(0);
        lock.acquire();
    }

    @Test
    public void acquireUninterruptibly() {
        Semaphore lock = new Semaphore(2);
        lock.acquireUninterruptibly();
        lock.acquireUninterruptibly();
        lock.acquireUninterruptibly();
    }

    @Test
    public void tryAcquire() {
        Semaphore lock = new Semaphore(2);
        boolean a = lock.tryAcquire();
        boolean a1 = lock.tryAcquire();
        boolean a2 = lock.tryAcquire();
    }

    @Test
    public void tryAcquireTime() throws InterruptedException {
        Semaphore lock = new Semaphore(2);
        lock.tryAcquire(10, TimeUnit.SECONDS);
    }

    @Test
    public void release() throws InterruptedException {
        Semaphore lock = new Semaphore(2);
        lock.acquire(2);
        int a = lock.availablePermits();
        lock.release();
        int b = lock.availablePermits();
    }

    @Test
    public void acquire1() throws InterruptedException {
        Semaphore lock = new Semaphore(3);
        lock.acquire(4);
    }

    @Test
    public void acquireUninterruptibly1() {
        Semaphore lock = new Semaphore(3);
        lock.acquireUninterruptibly(4);
    }

    @Test
    public void tryAcquire1() {
        Semaphore lock = new Semaphore(1);
        boolean b = lock.tryAcquire(2);
    }

    @Test
    public void tryAcquire2() throws InterruptedException {
        Semaphore lock = new Semaphore(1);
        boolean b = lock.tryAcquire(2, 10, TimeUnit.SECONDS);
    }

    @Test
    public void release2() {
        Semaphore lock = new Semaphore(1);
        lock.release(2);
    }

    @Test
    public void availablePermits1() {
        Semaphore lock = new Semaphore(3);
        int i = lock.availablePermits();
    }

    @Test
    public void drainPermits1() {
        Semaphore lock = new Semaphore(3);
        int i = lock.drainPermits();
        int a = lock.availablePermits();
    }

    @Test
    public void isFair() {
        Semaphore lock = new Semaphore(3);
        lock.isFair();
    }

    @Test
    public void hasQueuedThreads() throws InterruptedException {
        Semaphore lock = new Semaphore(2);
        boolean b = lock.hasQueuedThreads();
    }

    @Test
    public void getQueueLength() {
        Semaphore lock = new Semaphore(3);
        int b = lock.getQueueLength();
    }

}
