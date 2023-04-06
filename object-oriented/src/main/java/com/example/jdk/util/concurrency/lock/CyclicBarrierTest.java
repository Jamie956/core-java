package com.example.jdk.util.concurrency.lock;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierTest {
    @Test
    public void cons() {
        CyclicBarrier c = new CyclicBarrier(3, () -> System.out.println("action"));
    }

    @Test
    public void cons2() {
        CyclicBarrier c = new CyclicBarrier(3);
    }

    @Test
    public void await() {
        CyclicBarrier c = new CyclicBarrier(3, () -> System.out.println("action"));
        Runnable r = () -> {
            try {
                c.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }

    @Test
    public void awaitTimeOut() throws BrokenBarrierException, InterruptedException, TimeoutException {
        CyclicBarrier c = new CyclicBarrier(3);
        c.await(10, TimeUnit.SECONDS);
    }

    @Test
    public static void isBroken() throws InterruptedException {
        CyclicBarrier c = new CyclicBarrier(3, () -> System.out.println("action"));
        boolean b = c.isBroken();
    }

    @Test
    public void reset() {
        CyclicBarrier c = new CyclicBarrier(3);
        c.reset();
    }

    @Test
    public void getNumberWaiting() {
        CyclicBarrier c = new CyclicBarrier(3);
        int i = c.getNumberWaiting();
    }

    //----------------- 场景模拟 -----------------

    public static void demo1() {
        CyclicBarrier c = new CyclicBarrier(3, () -> System.out.println("action"));

        Runnable r = () -> {
            try {
                System.out.println(Thread.currentThread().getName());
                c.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        new Thread(r).start();
        new Thread(r).start();
    }

    public static void demo2() {
        CyclicBarrier c = new CyclicBarrier(3, () -> System.out.println("action"));

        Runnable r = () -> {
            try {
                System.out.println(Thread.currentThread().getName());
                c.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }

    public static void demo3() {
        CyclicBarrier c = new CyclicBarrier(3, () -> System.out.println("action"));

        Runnable r = () -> {
            try {
                System.out.println(Thread.currentThread().getName());
                c.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();

        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }

    public static void main(String[] args) throws Exception {
        demo3();
    }
}