package com.jamie.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    /**
     * 1 await 1 signal
     */
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

    /**
     * 2 await 1 signal
     */
    public static void case2() throws InterruptedException {
        ConditionTest conditionTest = new ConditionTest();
        Condition condition = conditionTest.condition;
        ReentrantLock lock = conditionTest.lock;

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " before lock condition await");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " after lock condition await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " before lock condition await");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " after lock condition await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        TimeUnit.SECONDS.sleep(1);
        t1.start();
        t2.start();
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

    /**
     * 2 await 1 signalAll
     */
    public static void case3() throws InterruptedException {
        ConditionTest conditionTest = new ConditionTest();
        Condition condition = conditionTest.condition;
        ReentrantLock lock = conditionTest.lock;

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " before lock condition await");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " after lock condition await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " before lock condition await");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " after lock condition await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        TimeUnit.SECONDS.sleep(1);
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " execute Signal");
                //断点，上面两条线程此时都处于 await 状态
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
//        case1();
//        case2();
        case3();
    }

}