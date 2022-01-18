package com.jamie.concurrency.juc.pool;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.text.MessageFormat;
import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        LinkedBlockingQueue workQueue = new LinkedBlockingQueue<>(5);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1, 2, 1,
                TimeUnit.MILLISECONDS, workQueue,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        Runnable task = () -> {
            try {
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
        pool.execute(task);

        pool.execute(task);

        pool.shutdown();
    }

    public void t1() {
        int corePoolSize = 30;
        int maximumPoolSize = 200;
        long keepAliveTime = 10;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(80),
                new DefaultThreadFactory("defaultPool"),
                new ThreadPoolExecutor.AbortPolicy());
        executor.execute(() -> System.out.println(1));
        executor.shutdown();
    }
}