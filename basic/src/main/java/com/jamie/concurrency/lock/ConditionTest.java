package com.jamie.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    public static void work(ReentrantLock lock, Condition condition) {
        lock.lock();
        try {
            System.out.println("lock condition await 之前");
            TimeUnit.SECONDS.sleep(1);
            condition.await();
            System.out.println("lock condition await 之后");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void continueWork(ReentrantLock lock, Condition condition) {
        lock.lock();
        try {
            System.out.println("Signal");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> work(lock, condition)).start();

        System.out.println("3 秒之后 signal lock condition");
        TimeUnit.SECONDS.sleep(3);
        continueWork(lock, condition);
    }

}