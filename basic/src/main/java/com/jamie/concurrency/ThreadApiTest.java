package com.jamie.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Thread implements Runnable
 * <p>
 * 1）Runnable提供run方法，无法通过throws抛出异常，
 * 所有CheckedException必须在run方法内部处理。Callable提供call方法，直接抛出Exception异常。
 * <p>
 * 2）Runnable的run方法无返回值，Callable的call方法提供返回值用来表示任务运行的结果
 * <p>
 * 3）Runnable可以作为Thread构造器的参数，通过开启新的线程来执行，
 * 也可以通过线程池来执行。而Callable只能通过线程池执行。
 */
public class ThreadApiTest {
    private static final Runnable run = () -> {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    /**
     * main 先执行 start，再创建新线程执行耗时任务，最后执行 end
     * 新线程耗时任务执行完输出
     */
    public static void noJoin() {
        System.out.println("start");
        new Thread(run).start();
        System.out.println("end");
    }

    public static void joinTest1() throws InterruptedException {
        System.out.println("start");
        Thread t = new Thread(run);
        t.start();
        //Waits for this thread to die.
        //调度线程t，t任务执行完再调度线程main
        t.join();
        System.out.println("end");
    }

    public static void joinTest2() throws InterruptedException {
        System.out.println("start");
        Thread t = new Thread(run);
        t.start();

        //Waits at most milliseconds for this thread to die.
        //调度线程t，时间片只有1s，当1s过后，调度线程main继续执行
        t.join(1000);
        System.out.println("end");
    }

    public static void joinTest3() throws InterruptedException {
        System.out.println("start");
        Thread t = new Thread(run);
        t.start();

        //给线程t分配4s时间片，而t任务只需要3s，所以任务t完成后再执行 end
        t.join(4000);
        System.out.println("end");
    }

    public void interruptTest() {
        Thread.interrupted();
        Thread.currentThread().interrupt();
    }

    public void yieldTest() {
        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + i + "  yield");
                    Thread.yield();
                }
            }
        };
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }

    public void yieldTest2() {
        Object lock = new Object();
        Runnable task = () -> {
            //yield不会释放锁
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ":  " + i);
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":  " + i + "  yield");
                        Thread.yield();
                    }
                }
            }
        };
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }

    public void curThread() {
        Thread thread = Thread.currentThread();
        Thread.interrupted();
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
//        noJoin();
//        joinTest1();
//        joinTest2();
//        joinTest3();
//        new ThreadApiTest().yieldTest();
//        new ThreadApiTest().yieldTest2();
        new ThreadApiTest().curThread();
    }

}
