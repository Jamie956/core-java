package com.jamie.concurrency;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class SyncObject {

    public void noSync() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同一实例的 不带锁的同一个方法，乱序执行
     */
    @Test
    public void noSyncTest() throws InterruptedException {
        SyncObject obj = new SyncObject();
        new Thread(() -> obj.noSync()).start();
        new Thread(() -> obj.noSync()).start();

        //当前线程等待测试线程执行完成
        TimeUnit.SECONDS.sleep(10);
    }

    public void thisSync() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 多线程执行
     * 同一实例的 带同一把锁的方法，顺序执行
     */
    @Test
    public void thisSyncTest() throws InterruptedException {
        SyncObject obj = new SyncObject();
        new Thread(() -> obj.thisSync()).start();
        new Thread(() -> obj.thisSync()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * 多线程执行
     * 不同实例的 带不同锁的方法，乱序执行
     */
    @Test
    public void thisSync2Test() throws InterruptedException {
        SyncObject x = new SyncObject();
        SyncObject y = new SyncObject();

        new Thread(() -> x.thisSync()).start();
        new Thread(() -> y.thisSync()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * 多线程执行同一个实例的带锁方法和不带锁方法，乱序执行
     */
    @Test
    public void thisSyncNoSync() throws InterruptedException {
        SyncObject obj = new SyncObject();
        new Thread(() -> obj.thisSync()).start();
        new Thread(() -> obj.noSync()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    public void thisSync2() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 多线程执行同一实例的带同一把锁的不同方法，顺序执行
     */
    @Test
    public void twoThisSync() throws InterruptedException {
        SyncObject obj = new SyncObject();
        new Thread(() -> obj.thisSync()).start();
        new Thread(() -> obj.thisSync2()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    public static synchronized void staticSync() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void staticSync2() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多线程执行带锁的静态方法，顺序执行
     * 类锁/全局锁
     */
    @Test
    public void staticSyncTest() throws InterruptedException {
        new Thread(() -> SyncObject.staticSync()).start();
        new Thread(() -> SyncObject.staticSync2()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * 多线程执行实例的带锁方法和静态带锁方法，乱序执行
     */
    @Test
    public void thisSyncStaticSyncTest() throws InterruptedException {
        SyncObject x = new SyncObject();
        new Thread(() -> x.thisSync2()).start();
        new Thread(() -> SyncObject.staticSync()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

}
