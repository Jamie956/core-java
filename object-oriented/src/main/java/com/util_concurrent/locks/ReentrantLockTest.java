package com.util_concurrent.locks;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        lockTest2();
    }

    @Test
    public void cons() {
        ReentrantLock lock = new ReentrantLock();
    }

    @Test
    public void cons2() {
        ReentrantLock lock = new ReentrantLock(true);
    }

    @Test
    public void lock() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }

    @Test
    public void lockInterruptibly() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.lockInterruptibly();
    }

    @Test
    public void tryLock() {
        ReentrantLock lock = new ReentrantLock();
        boolean b = lock.tryLock();
    }

    @Test
    public void tryLockTime() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        boolean b = lock.tryLock(10, TimeUnit.SECONDS);
    }

    @Test
    public void unlock() {
        ReentrantLock lock = new ReentrantLock();
        lock.unlock();
    }

    @Test
    public void newCondition() {
        ReentrantLock lock = new ReentrantLock();
        Condition c = lock.newCondition();
    }

    @Test
    public void getHoldCount() {
        ReentrantLock lock = new ReentrantLock();
        int hc = lock.getHoldCount();
    }

    @Test
    public void isHeldByCurrentThread() {
        ReentrantLock lock = new ReentrantLock();
        boolean b = lock.isHeldByCurrentThread();
    }

    @Test
    public void isLocked() {
        ReentrantLock lock = new ReentrantLock();
        boolean b = lock.isLocked();
    }

    @Test
    public void isFair() {
        ReentrantLock lock = new ReentrantLock();
        boolean b = lock.isFair();
    }

    @Test
    public void hasQueuedThreads() {
        ReentrantLock lock = new ReentrantLock();
        boolean b = lock.hasQueuedThreads();
    }

    @Test
    public void hasQueuedThread() {
        ReentrantLock lock = new ReentrantLock();
        boolean b = lock.hasQueuedThread(new Thread());
    }

    @Test
    public void getQueueLength() {
        ReentrantLock lock = new ReentrantLock();
        int b = lock.getQueueLength();
    }

    @Test
    public void getQueuedThreads() {
        ReentrantLock lock = new ReentrantLock();
        boolean b = lock.hasWaiters(lock.newCondition());
    }

    @Test
    public void getWaitQueueLength() {
        ReentrantLock lock = new ReentrantLock();
        int a = lock.getWaitQueueLength(lock.newCondition());
    }

    @Test
    public void toString1() {
        ReentrantLock lock = new ReentrantLock();
        String s = lock.toString();
    }

    public static void lockTest() {
        //Thread Suspend
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            //Thread Suspend
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //Thread Suspend
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            //Thread Suspend
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //Thread Suspend
                lock.unlock();
            }
        }).start();
    }

    public static void fairLockTest() {
        //Thread Suspend
        ReentrantLock lock = new ReentrantLock(true);
        new Thread(() -> {
            //Thread Suspend
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(lock.toString() + " " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //Thread Suspend
                lock.unlock();
            }
        }).start();
    }

    /**
     * 3个线程使用同一个 ReentrantLock，上锁的代码其他线程不可进入
     */
    public static void lockTest2() {
        //Thread Suspend
        ReentrantLock lock = new ReentrantLock();
        Runnable r = () -> {
            //Thread Suspend
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //Thread Suspend
                lock.unlock();
            }
        };

        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }

    /**
     * 可重入锁
     * 同一个线程能进入 同一把锁 每一个锁住的代码块
     */
    public static void reLockTest() {
        ReentrantLock lock = new ReentrantLock();

        Runnable r = () -> {
            String name = Thread.currentThread().getName();

            try {
                lock.lock();
                Thread.sleep(1000);
                System.out.println(name + "第一次嵌套内lock 的 state=" + lock.getHoldCount());

                //Thread Suspend
                lock.lock();
                Thread.sleep(1000);
                System.out.println(name + "第二次嵌套内lock 的 state=" + lock.getHoldCount());

                lock.lock();
                Thread.sleep(1000);
                System.out.println(name + "第三次嵌套内lock 的 state=" + lock.getHoldCount());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                lock.unlock();
                lock.unlock();
                System.out.println(name + " 锁释放");
            }
        };

        new Thread(r).start();
        new Thread(r).start();

    }

    /**
     * 必须要a锁 全部执行完
     */
    public static void twoLockTest() {
        ReentrantLock a = new ReentrantLock();
        ReentrantLock b = new ReentrantLock();

        Runnable r = () -> {
            try {
                a.lock();
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " outer i=" + i);
                }

                b.lock();
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " inner i=" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                a.unlock();
                b.unlock();
            }
        };

        new Thread(r).start();
        new Thread(r).start();
    }

    /**
     * 第一条线程 跑完a lock 代码块，第二条线程开始跑a lock 代码块，
     * 第一条线程的 b lock 代码块 和 第二条线程的 a lock 交替执行
     */
    public static void twoLockTest2() {
        ReentrantLock a = new ReentrantLock();
        ReentrantLock b = new ReentrantLock();

        Runnable r = () -> {
            try {
                a.lock();
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " lock 1 i=" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
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
        };
        new Thread(r).start();
        new Thread(r).start();
    }
}
