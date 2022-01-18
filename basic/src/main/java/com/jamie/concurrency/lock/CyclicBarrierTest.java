package com.jamie.concurrency.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void task(CyclicBarrier barrier) {
        try {
            System.out.println(Thread.currentThread().getName() + " 来了" + barrier.getNumberWaiting() + "个人");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " 在路上");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    /**
     * 不够人不发车
     */
    public static void test1() {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("人齐出发"));

        System.out.println("够 " + barrier.getParties() + "个人才开车");
        new Thread(() -> task(barrier)).start();
        new Thread(() -> task(barrier)).start();
    }

    /**
     * 刚好够人 发车
     */
    public static void test2() {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("人齐出发！"));

        System.out.println("够 " + barrier.getParties() + "个人才开车");
        new Thread(() -> task(barrier)).start();
        new Thread(() -> task(barrier)).start();
        new Thread(() -> task(barrier)).start();
    }

    /**
     * 人太多，等下一波
     */
    public static void test3() {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("人齐出发"));

        System.out.println("够 " + barrier.getParties() + "个人才开车");
        new Thread(() -> task(barrier)).start();
        new Thread(() -> task(barrier)).start();
        new Thread(() -> task(barrier)).start();

        new Thread(() -> task(barrier)).start();
        new Thread(() -> task(barrier)).start();
        new Thread(() -> task(barrier)).start();
    }

    public static void main(String[] args) {
        test3();
    }
}