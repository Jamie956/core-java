package com.jamie.concurrency;

import java.util.concurrent.TimeUnit;

public class JoinTest {

    public static void noJoin(){
        System.out.println("start");
        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        System.out.println("end");
    }

    public static void joinTest1() throws InterruptedException {
        System.out.println("start");
        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        //Waits for this thread to die. 线程t加入当前main线程，等待线程t死亡才继续执行main线程
        t.join();//等待任务线程执行完，main线程才继续往下执行

        System.out.println("end");
    }

    public static void joinTest2() throws InterruptedException {
        System.out.println("start");
        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        //Waits at most milliseconds for this thread to die.
        t.join(1000);//任务线程join 1秒后，main线程继续执行，任务线程需要执行3秒，所以join 的1秒过后 任务线程还要完成执行任务

        System.out.println("end");
    }

    public static void joinTest3() throws InterruptedException {
        System.out.println("start");
        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        t.join(4000);//任务线程join 4秒后，main线程继续执行，任务线程消耗3秒，索引join 4秒时，任务线程已经执行完3秒任务

        System.out.println("end");
    }

    public static void main(String[] args) throws InterruptedException {
//        noJoin();

//        joinTest1();
//        joinTest2();
        joinTest3();
    }

}
