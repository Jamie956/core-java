package com.example.jdk.util.concurrency;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class AtomicIntegerTest {

    @Test
    public void cons1() {
        AtomicInteger a = new AtomicInteger(1);
    }

    @Test
    public void cons2() {
        AtomicInteger a = new AtomicInteger();
    }

    @Test
    public void lazySet() {
        AtomicInteger a = new AtomicInteger();
        a.lazySet(1000);
    }

    @Test
    public void getAndSet() {
        AtomicInteger a = new AtomicInteger();
        int i = a.getAndSet(1000);
        int j = a.get();
    }

    @Test
    public void compareAndSet() {
        AtomicInteger a = new AtomicInteger();
        boolean b = a.compareAndSet(1, 1000);
    }

    @Test
    public void getAndIncrement() {
        AtomicInteger a = new AtomicInteger(3);
        int i = a.getAndIncrement();
        int k = a.get();
    }

    @Test
    public void getAndDecrement() {
        AtomicInteger a = new AtomicInteger(3);
        int i = a.getAndDecrement();
        int k = a.get();
    }

    @Test
    public void getAndAdd() {
        AtomicInteger a = new AtomicInteger(10);
        int b = a.getAndAdd(2);
        int c = a.get();
        int d = a.getAndAdd(-3);
        int e = a.get();
    }

    @Test
    public void incrementAndGet() {
        AtomicInteger a = new AtomicInteger(3);
        int i = a.incrementAndGet();
        int k = a.get();
    }

    @Test
    public void decrementAndGet() {
        AtomicInteger a = new AtomicInteger(3);
        int i = a.decrementAndGet();
        int k = a.get();
    }

    @Test
    public void addAndGet() {
        AtomicInteger a = new AtomicInteger(3);
        int i = a.addAndGet(2);
        int k = a.get();
    }

    @Test
    public void getAndUpdate() {
        AtomicInteger a = new AtomicInteger(3);
        IntUnaryOperator f = e -> e + 5;
        int i = a.getAndUpdate(f);
        int k = a.get();
    }

    @Test
    public void updateAndGet() {
        AtomicInteger a = new AtomicInteger(3);
        IntUnaryOperator f = e -> e + 5;
        int i = a.updateAndGet(f);
        int k = a.get();
    }

    @Test
    public void getAndAccumulate() {
        AtomicInteger a = new AtomicInteger(3);
        IntBinaryOperator f = (i, k) -> i * 2 + k * 3;
        int i = a.getAndAccumulate(2, f);
        int k = a.get();
    }

    @Test
    public void accumulateAndGet() {
        AtomicInteger a = new AtomicInteger(3);
        IntBinaryOperator f = (i, k) -> i * 2 + k * 3;
        int i = a.accumulateAndGet(2, f);
        int k = a.get();
    }

    //------------------------- 场景模拟 -------------------------

    public static void main(String[] args) {
        unsafe();
    }

    public static void safe() {
        AtomicInteger i = new AtomicInteger(0);
        Runnable r = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i.getAndIncrement());
        };

        for (int j = 0; j < 10; j++) {
            new Thread(r).start();
        }
    }

    public static void unsafe() {
        Runnable r = new Runnable() {
            int j = 0;

            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getI());

            }

            //1.读取i; 2.运算i+1; 3.i的值刷到主存
            public int getI() {
                return j++;
            }
        };
        for (int j = 0; j < 10; j++) {
            new Thread(r).start();
        }
    }

}
