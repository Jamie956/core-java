package com.jamie.concurrency.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    static Semaphore semaphore = new Semaphore(3);

    static void task(int sec) {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " start state=" + semaphore.availablePermits());
            TimeUnit.SECONDS.sleep(sec);
            System.out.println(Thread.currentThread().getName() + " end state=" + semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    /**
     * 信号量，分配n把共享锁
     * 分配3把共享锁，也就设定state的值
     * nonfairTryAcquireShared for 自旋，只有当 state分配完即小于0 或者 CAS更新成功，就返回剩余的state，
     * 如果state小于0 就会执行中断doAcquireSharedInterruptibly(arg) 自旋等候，直到try 获取到共享锁为止
     */
    /*
    //核心方法 Semaphore Sync
    abstract static class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 1192457210091910933L;

        Sync(int permits) {
            setState(permits);
        }

        final int getPermits() {
            return getState();
        }

        final int nonfairTryAcquireShared(int acquires) {
            for (;;) {
                int available = getState();
                int remaining = available - acquires;
                if (remaining < 0 ||
                    compareAndSetState(available, remaining))
                    return remaining;
            }
        }
     */
    public static void main(String[] args) {
        new Thread(() -> task(2)).start();
        new Thread(() -> task(2)).start();
        new Thread(() -> task(6)).start();

        new Thread(() -> task(2)).start();
        new Thread(() -> task(2)).start();
    }

}

