package com.example.jdk.util.concurrency.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {
    @Test
    public void cons() {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
    }

    @Test
    public void cons2() {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock(true);
    }

    @Test
    public void writeLock() {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock wl = l.writeLock();
    }

    @Test
    public void readLock() {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock rl = l.readLock();
    }

    @Test
    public void lockInterruptibly() throws InterruptedException {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock rl = l.readLock();
        rl.lockInterruptibly();
    }

    @Test
    public void tryLock() {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock rl = l.readLock();
        boolean b = rl.tryLock();
    }

    @Test
    public void tryLock2() throws InterruptedException {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock rl = l.readLock();
        boolean b = rl.tryLock(3, TimeUnit.SECONDS);
    }

    @Test
    public void unlock() {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock rl = l.readLock();
        boolean b = rl.tryLock();
        rl.unlock();
    }

    @Test
    public void lock() {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock wl = l.writeLock();
        wl.lock();
    }

    @Test
    public void lockInterruptibly2() throws InterruptedException {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock wl = l.writeLock();
        wl.lockInterruptibly();
    }

    @Test
    public void tryLock3() {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock wl = l.writeLock();
        boolean b = wl.tryLock();
    }

    @Test
    public void tryLock4() throws InterruptedException {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock wl = l.writeLock();
        boolean b = wl.tryLock(3, TimeUnit.SECONDS);
    }

    @Test
    public void unlock2() {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock wl = l.writeLock();
        wl.lock();
        wl.unlock();
    }

    @Test
    public void newCondition() {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock wl = l.writeLock();
        Condition c = wl.newCondition();
    }

    @Test
    public void isHeldByCurrentThread() {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock wl = l.writeLock();
        boolean b = wl.isHeldByCurrentThread();
    }

    @Test
    public void getHoldCount() {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock wl = l.writeLock();
        wl.lock();
        int b = wl.getHoldCount();
    }

    @Test
    public void isFair() {
        ReentrantReadWriteLock l = new ReentrantReadWriteLock();
        boolean b = l.isFair();
    }


    //-----------------------------

    public static void main(String[] args) {
        ReentrantReadWriteLockTest t = new ReentrantReadWriteLockTest();
        t.testReadWriteLock();
    }

    /**
     * 测试 读写锁，每把写锁可以同时进行，读锁只能一把把进行
     */
    public void testReadWriteLock() {
        //Suspend Thread
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        Runnable r = () -> {
            //Suspend Thread
            ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
            //Suspend Thread
            readLock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " read " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        };

        Runnable w = () -> {
            ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
            //Suspend Thread
            writeLock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " write " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        };


        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();

        new Thread(w).start();
        new Thread(w).start();
        new Thread(w).start();
    }

    /**
     * 测试：读锁单线程lock方法
     */
    public void readLockFirstLock() {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock lock = rwLock.readLock();
        Runnable r = () -> {
            try {
                //Suspend
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //Suspend
                lock.unlock();
            }
        };
        new Thread(r).start();
    }

    /**
     * 测试：读锁单线程lock方法重入
     */
    public void readLockReenterLock() {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock lock = rwLock.readLock();
        Runnable r = () -> {
            try {
                //Suspend
                lock.lock();
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                lock.unlock();
            }
        };
        new Thread(r).start();
    }

    /**
     * 测试：读锁多线程lock方法
     */
    public void readLockMultiThread() {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock lock = rwLock.readLock();
        Runnable r = () -> {
            try {
                //Suspend
                lock.lock();
                //线程1 Suspend
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //Suspend
                lock.unlock();
            }
        };
        new Thread(r).start();
        new Thread(r).start();
    }

    /**
     * 测试：读锁多线程lock方法重入；unlock方法
     */
    public void readLockMultiThreadReenter() {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock lock = rwLock.readLock();
        Runnable r = () -> {
            try {
                lock.lock();
                lock.lock();
                //线程2 Suspend
                lock.lock();
                //线程1 Suspend
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                lock.unlock();
                lock.unlock();
            }
        };
        new Thread(r).start();
        new Thread(r).start();
    }

    /**
     * 测试：读锁(公平锁)多线程lock方法
     */
    public void readLockFairLock() {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);
        ReentrantReadWriteLock.ReadLock lock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock wLock = rwLock.writeLock();

        Runnable r = () -> {
            try {
                //Suspend 1.读锁准备要CAS操作; 3.CAS fail
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
        Runnable w = () -> {
            try {
                wLock.lock();
                //Suspend 2.抢占锁
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                wLock.unlock();
            }
        };

        new Thread(r).start();
        new Thread(w).start();
    }

    /**
     * 测试：读锁单线程 tryLock 方法
     */
    public void readLockTryLock() {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock lock = rwLock.readLock();
        Runnable r = () -> {
            try {
                //Suspend
                lock.tryLock();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
        new Thread(r).start();
    }

    /**
     * 测试：读锁 tryLock 超时方法
     */
    public void readLockTryLockTimeout() {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);
        ReentrantReadWriteLock.ReadLock rLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock wLock = rwLock.writeLock();

        Runnable r = () -> {
            try {
                //Suspend
                rLock.tryLock(10, TimeUnit.SECONDS);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rLock.unlock();
            }
        };
        Runnable w = () -> {
            try {
                wLock.lock();
                //Suspend 抢占锁
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                wLock.unlock();
            }
        };

        new Thread(r).start();
        new Thread(w).start();
    }

    /**
     * 测试：写锁单线程lock方法
     */
    public void writeLockFirstLock() {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock lock = rwLock.writeLock();
        Runnable r = () -> {
            try {
                //Suspend
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //Suspend
                lock.unlock();
            }
        };
        new Thread(r).start();
    }

    /**
     * 测试：写锁单线程lock方法
     */
    public void writeLockReenter() {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock lock = rwLock.writeLock();
        Runnable r = () -> {
            try {
                lock.lock();
                //Suspend
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //Suspend
                lock.unlock();
                lock.unlock();
            }
        };
        new Thread(r).start();
    }

    /**
     * 测试：单线程 读锁持锁时，写锁lock方法获取锁
     */
    public void writeLockWRLock() {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock wLock = rwLock.writeLock();
        ReentrantReadWriteLock.ReadLock rLock = rwLock.readLock();
        Runnable r = () -> {
            try {
                rLock.lock();
                //Suspend
                wLock.lock();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                wLock.unlock();
                rLock.unlock();
            }
        };
        new Thread(r).start();
    }

    /**
     * 测试：写锁单线程 tryLock 方法
     */
    public void writeLockTryLock() {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock lock = rwLock.writeLock();
        Runnable r = () -> {
            try {
                //Suspend
                lock.tryLock();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
        new Thread(r).start();
    }
}