package com.jamie.concurrency.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void noLockWork() {
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void work(ReentrantLock lock) {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 没有锁时，任务无序执行
     */
    public static void test0() {
        new Thread(() -> noLockWork()).start();
        new Thread(() -> noLockWork()).start();
        new Thread(() -> noLockWork()).start();
    }

    /**
     * 3个线程使用同一个 ReentrantLock，上锁的代码其他线程不可进入
     */
    public static void test1() {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> work(lock)).start();
        new Thread(() -> work(lock)).start();
        new Thread(() -> work(lock)).start();
    }

    public static void reentrantWork(ReentrantLock lock) {
        try {
            lock.lock();//state==1
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "第一次嵌套内lock 的 state=" + lock.getHoldCount());
            try {
                lock.lock();//state==2
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "第二次嵌套内lock 的 state=" + lock.getHoldCount());
                try {
                    lock.lock();//state==3
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "第三次嵌套内lock 的 state=" + lock.getHoldCount());
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "第四次嵌套外lock 的 state=" + lock.getHoldCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 可重入锁
     * 同一个线程能进入 同一把锁 每一个锁住的代码块，通过CAS 修改state，嵌套的lock 会累计计算，嵌套外的会重置
     */
    public static void test2() {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> reentrantWork(lock)).start();
        new Thread(() -> reentrantWork(lock)).start();
    }

    public static void twoLockWork(ReentrantLock a, ReentrantLock b) {
        try {
            a.lock();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "第一次嵌套内lock 的 state=" + a.getHoldCount());
            try {
                b.lock();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "第二次嵌套内lock 的 state=" + b.getHoldCount());
            } finally {
                b.unlock();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            a.unlock();
        }
    }

    public static void twoLock() {
        ReentrantLock a = new ReentrantLock();
        ReentrantLock b = new ReentrantLock();
        new Thread(() -> twoLockWork(a, b)).start();
        new Thread(() -> twoLockWork(a, b)).start();
    }

    public static void twoLockWork2(ReentrantLock a, ReentrantLock b) {
        try {
            a.lock();
            for (int i = 0; i < 10; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " lock 1 i=" + i);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            a.unlock();
        }

        try {
            b.lock();
            for (int i = 0; i < 10; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " lock 2 i=" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            b.unlock();
        }
    }

    /**
     * 第一条线程 跑完a lock 代码块，第二条线程开始跑a lock 代码块，第一条线程的 b lock 代码块 和 第二条线程的 a lock 交替执行
     */
    public static void twoLock2() {
        ReentrantLock a = new ReentrantLock();
        ReentrantLock b = new ReentrantLock();
        new Thread(() -> twoLockWork2(a, b)).start();
        new Thread(() -> twoLockWork2(a, b)).start();
    }

    public static void main(String[] args) {
//        test2();
//        twoLock();
        twoLock2();
    }
}
