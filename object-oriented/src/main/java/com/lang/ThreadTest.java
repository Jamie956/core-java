package com.lang;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    public static void main(String[] args) throws Exception {
        join();
    }

    @Test
    public void currentThread() {
        Thread t = Thread.currentThread();
    }

    public static void yield() {
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                Thread.yield();
            }
        };
        Thread t1 = new Thread(task, "低-优先度线程");
        Thread t2 = new Thread(task, "高-优先度线程");

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
    }

    public static void yieldLock() {
        Object lock = new Object();
        Runnable task = () -> {
            //yield不会释放锁
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ":  " + i);
                    Thread.yield();
                }
            }
        };
        Thread t1 = new Thread(task, "低-优先度线程");
        Thread t2 = new Thread(task, "高-优先度线程");

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
    }

    public static void sleep() throws InterruptedException {
        Thread.sleep(100);
    }

    @Test
    public void sleep2() throws InterruptedException {
        Thread.sleep(100, 100);
    }

    @Test
    public void cons() {
        Thread thread = new Thread(new ThreadGroup("my-group"),
                () -> System.out.println("hi"), "my-thread", 0);
        thread.start();
    }

    @Test
    public void start() {
        new Thread(() -> System.out.println("hh")).start();
    }

    @Test
    public void run() {
        new Thread(() -> System.out.println("hh")).run();
    }

    public static void interrupt() {
        Thread t = new Thread();
        t.interrupt();
    }

    public static void interrupt2() {
        Thread testThread = new Thread(() -> {
            //第一种情况
//            while (!Thread.interrupted()) {
//            }
//            System.out.println("中断状态：" + Thread.currentThread().isInterrupted()); //false

            //第二种情况
            while (!Thread.currentThread().isInterrupted()) {
            }
            System.out.println("中断状态：" + Thread.currentThread().isInterrupted()); //true
        });
        testThread.start();
        try {
            Thread.sleep(5000);
            System.out.println("设置线程中断标志");
            testThread.interrupt();
            Thread.sleep(1000);
            for (int i = 0; i < 10; i++) {
                System.out.println("检测线程的中断信号：" + testThread.isInterrupted());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void interrupted() {
        Thread.interrupted();
    }

    public static void isInterrupted() {
        new Thread().isInterrupted();
    }

    public static void isAlive() throws InterruptedException {
        Thread t = new Thread(() -> {
            while (!Thread.interrupted()) {
            }
        });
        t.start();
        System.out.println(t.isAlive());
        t.interrupt();

        Thread.sleep(5000);
        //线程被中断，不再循环，走向死亡
        System.out.println(t.isAlive());
    }

    public static void setPriority() {
        new Thread().setPriority(5);
    }

    @Test
    public void setName() {
        Thread t = new Thread();
//        t.start();
        t.setName("asas");
    }

    @Test
    public void activeCount() {
        new Thread().start();
        new Thread().start();
        new Thread().start();
        int i = Thread.activeCount();
    }

    @Test
    public void enumerate() {
        Thread[] ts = new Thread[]{new Thread(), new Thread(), new Thread(), new Thread()};
        int i = Thread.enumerate(ts);
    }

    public static void join() throws InterruptedException {
        Thread t = new Thread(() -> {
            while (!Thread.interrupted()) {
            }
        });
        t.start();
        t.join(3000);
    }

    public static void join1() throws InterruptedException {
        System.out.println("start");
        Runnable run = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread t = new Thread(run);
        t.start();
        //main线程等待，直到t执行完任务
        t.join();
        System.out.println("end");
    }

    public static void join2() throws InterruptedException {
        System.out.println("start");
        Runnable run = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread t = new Thread(run);
        t.start();

        //main线程等待1000毫秒
        t.join(1000);
        System.out.println("end");
    }

    @Test
    public void dumpStack() {
        Thread.dumpStack();
    }

    @Test
    public void setDaemon() {
        Thread t = new Thread();
//        t.start();//存活线程不能修改 daemon
        t.setDaemon(true);
        t.start();
    }

    public static void checkAccess() {
        new Thread().checkAccess();
    }

    public static void toString1() {
        new Thread().toString();
    }

    @Test
    public void getContextClassLoader() {
        ClassLoader contextClassLoader = new Thread().getContextClassLoader();
    }

    @Test
    public void setContextClassLoader() {
        new Thread().setContextClassLoader(ClassLoader.getSystemClassLoader());
    }

    @Test
    public void holdsLock() {
        Object o = new Object();
        synchronized (o) {
            System.out.println(1);
            boolean b = Thread.holdsLock(o);
            System.out.println(b);
        }
    }

    @Test
    public void getStackTrace() {
        Thread t = new Thread();
        t.start();
        StackTraceElement[] stackTrace = t.getStackTrace();
    }

    @Test
    public void getAllStackTraces() {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
    }

    public static void getState() {
        new Thread().getState();
    }

    public static void setDefaultUncaughtExceptionHandler() {
//        Thread.setDefaultUncaughtExceptionHandler();
    }

    public static void setUncaughtExceptionHandler() {
//        new Thread().setUncaughtExceptionHandler();
    }

}
