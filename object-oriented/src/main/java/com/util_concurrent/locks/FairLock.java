package com.util_concurrent.locks;

import java.util.concurrent.locks.ReentrantLock;

// 公平锁按先后获取锁
public class FairLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println(finalI + " before get lock");
                    lock.lock();
                    System.out.println(finalI + " get lock");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }).start();
        }
    }
}
