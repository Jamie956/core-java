package com.jamie.concurrency;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * 线程池工具类
 */
public class ThreadUtil {
    /**
     * 线程池实例
     */
    private static ThreadPoolExecutor executor = null;
    /**
     * 核心线程数
     */
    private static final Integer THREADPOOL_COREPOOLSIZE = 30;
    /**
     * 最大线程数
     */
    private static final Integer THREADPOOL_MAXPOOLSIZE = 200;
    /**
     * 线程等待回收的存活时间，单位：分钟
     */
    private static final long THREADPOOL_KEEPALIVETIME = 10;

    /**
     * 初始化线程池
     * 线程池拒绝策略为默认的拒绝策略，如果不能加入工作队列就抛出RejectedExecutionException异常
     */
    static {

        executor = new ThreadPoolExecutor(THREADPOOL_COREPOOLSIZE,
                THREADPOOL_MAXPOOLSIZE,
                THREADPOOL_KEEPALIVETIME,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(80),
                new DefaultThreadFactory("defaultPool"),
                new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 使用线程池运行任务，线程无返回值
     * @param task
     */
    public static void execute(Runnable task){
        executor.execute(task);
    }

    /**
     * 使用线程池提交异步任务，任务运行带返回值
     * @param task 实现了Callable接口的线程
     * @return
     */
    public static Future submit(Callable task){
        return executor.submit(task);
    }

    /**
     * 停止线程池
     */
    public static void shutdown() {
        executor.shutdown();
    }

    public static ThreadPoolExecutor getExecutor(){
        return executor;
    }
}
