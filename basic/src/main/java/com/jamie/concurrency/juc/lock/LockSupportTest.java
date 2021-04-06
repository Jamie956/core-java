package com.jamie.concurrency.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void unpartMain(Thread thread) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " unpark main线程");
        LockSupport.unpark(thread);
    }

    /**
     * main线程 part 了自己，work 线程 unpart main线程
     * 主线程 park 之后，不再往下执行，直到被某条线程对主线程 unpark，主线程才继续执行
     */
    public static void unpartMain() {
        Thread main = Thread.currentThread();
        new Thread(() -> unpartMain(main)).start();

        System.out.println("main 线程 park 自己之前");
        LockSupport.park(main);
        System.out.println("main 线程 park 自己之后");
    }


    public static void partSelf() {
        System.out.println("task 将自己part 起来，等待其他线程 unpart");
        LockSupport.park(Thread.currentThread());
    }

    /**
     * task线程 part 了自己，main 线程 unpart task线程
     */
    public static void partSelfTest() {
        Thread thread = new Thread(() -> partSelf());
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main unpart partSelf 之前");
        LockSupport.unpark(thread);
        System.out.println("main unpart partSelf 之后");

    }

    public static void main(String[] args) {
        unpartMain();
//        partSelfTest();
    }

}
