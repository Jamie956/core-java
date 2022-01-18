package com.jamie.concurrency.juc.pool;

import java.text.MessageFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        new ThreadPoolExecutorTest().t2();
    }

    /**
     * 超过最大线程数时，拒绝策略的执行
     */
    public void t0() {
        //任务最大个数：队列大小+线程池最大线程数
        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(3);
        //Executors.defaultThreadFactory(): 实例化一个DefaultThreadFactory
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1, 2, 1,
                TimeUnit.MILLISECONDS, workQueue,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        Runnable task = () -> {
            try {
                //断点阻塞各个线程执行任务
                System.out.println(MessageFormat.format("{0} start: pool size={1} blocking queue size={2}",
                        Thread.currentThread().getName(), pool.getPoolSize(), workQueue.size()));

                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        pool.execute(task);

        pool.execute(task);
        pool.execute(task);
        pool.execute(task);

        pool.execute(task);

        //断点，队列3个任务+最大线程数2，超过时拒绝策略
        pool.execute(task);

        pool.shutdown();
    }

    /**
     * 最大线程数和队列满了时
     */
    public void t2() {
        //任务最大个数：队列大小+线程池最大线程数
        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(1);
        //Executors.defaultThreadFactory(): 实例化一个DefaultThreadFactory
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2, 3, 1,
                TimeUnit.HOURS, workQueue,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        Runnable task = () -> {
            try {
                //断点阻塞各个线程执行任务，减少空闲线程
                System.out.println(String.format("Thread %s start: poolSize=%s, queueSize=%s",
                        Thread.currentThread().getName(), pool.getPoolSize(), workQueue.size()));
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        //创建2条核心线程处理
        pool.execute(task);
        pool.execute(task);
        System.out.println(String.format("-> poolSize=%s, queueSize=%s", pool.getPoolSize(), workQueue.size()));
        //核心线程已满，任务加入队列
        pool.execute(task);
        System.out.println(String.format("-> poolSize=%s, queueSize=%s", pool.getPoolSize(), workQueue.size()));
        //此时，核心线程忙，队列满，创建新线程
        pool.execute(task);
        System.out.println(String.format("-> poolSize=%s, queueSize=%s", pool.getPoolSize(), workQueue.size()));

        pool.shutdown();
    }
}