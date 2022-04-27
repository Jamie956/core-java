package com.cat.lock;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class LockTest {
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        for (int i = 0; i < 50; i++) {
            DistributedLock lock = new DistributedLock();

            new Thread(() -> {
                try {
                    lock.acquire();
                    Thread.sleep(500);
                    lock.release();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
