package com.jamie.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    private static ExecutorService p = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        //线程不安全
        TaskAt taskAt = new TaskAt();

        //线程安全
//        TaskAt2 taskAt = new TaskAt2();
        for (int i = 0; i < 10; i++) {
            p.execute(taskAt);
        }

        p.shutdown();
    }

    static class TaskAt implements Runnable {
        private int i = 0;
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getI());
        }
        public int getI() {
            //i++包含3个步骤
            //1.读取i
            //2.运算i+1
            //3.i的值刷到主存
            return i++;
        }
    }

    static class TaskAt2 implements Runnable {
        private AtomicInteger i = new AtomicInteger();
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getI());
        }
        public int getI() {
            return i.getAndIncrement();
        }
    }
}
