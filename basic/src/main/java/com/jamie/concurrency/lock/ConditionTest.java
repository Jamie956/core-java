package com.jamie.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public static void case1() throws InterruptedException {
        ConditionTest conditionTest = new ConditionTest();
        Condition condition = conditionTest.condition;
        ReentrantLock lock = conditionTest.lock;

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " before lock condition await");
                //断点，suspend 设为 Thread
                condition.await();
                //断点，suspend 设为 Thread
                System.out.println(Thread.currentThread().getName() + " after lock condition await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " execute Signal");
                //断点，suspend 设为 Thread
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }

    public static void case2() throws InterruptedException {
        ConditionTest conditionTest = new ConditionTest();
        Condition condition = conditionTest.condition;
        ReentrantLock lock = conditionTest.lock;

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " before lock condition await");
                condition.await();
                //断点
                System.out.println(Thread.currentThread().getName() + " after lock condition await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " before lock condition await");
                condition.await();
                //断点
                System.out.println(Thread.currentThread().getName() + " after lock condition await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " execute Signal");
                //断点，上面两条线程此时都处于 await 状态
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
    public static void main(String[] args) throws InterruptedException {
//        case1();
        case2();
    }

}