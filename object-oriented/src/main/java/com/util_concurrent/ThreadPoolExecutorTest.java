package com.util_concurrent;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    @Test
    public void cons1() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
    }

    @Test
    public void cons2() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());
    }

    @Test
    public void cons3() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    @Test
    public void execute() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        pool.execute(() -> System.out.println("11"));
    }

    @Test
    public void shutdown() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        pool.shutdown();
    }

    @Test
    public void shutdownNow() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        Runnable r = () -> {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        };
        pool.execute(r);
        pool.execute(r);
        pool.execute(r);
        List<Runnable> list = pool.shutdownNow();
    }

    @Test
    public void isShutdown() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        boolean b = pool.isShutdown();
    }

    @Test
    public void isTerminating() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        boolean b = pool.isTerminating();
    }

    @Test
    public void isTerminated() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        boolean b = pool.isTerminated();
    }

    @Test
    public void awaitTermination() throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        boolean b = pool.awaitTermination(10, TimeUnit.SECONDS);
    }

    @Test
    public void setThreadFactory() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        pool.setThreadFactory(Executors.defaultThreadFactory());
    }

    @Test
    public void getThreadFactory() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        ThreadFactory tf = pool.getThreadFactory();
    }

    @Test
    public void setRejectedExecutionHandler() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
    }

    @Test
    public void getRejectedExecutionHandler() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        pool.getRejectedExecutionHandler();
    }

    @Test
    public void setCorePoolSize() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        pool.setCorePoolSize(6);
    }

    @Test
    public void getCorePoolSize() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        int c = pool.getCorePoolSize();
    }

    @Test
    public void prestartAllCoreThreads() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        int c = pool.prestartAllCoreThreads();
    }

    @Test
    public void allowsCoreThreadTimeOut() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        boolean c = pool.allowsCoreThreadTimeOut();
    }

    //
    @Test
    public void allowCoreThreadTimeOut() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                1L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        pool.allowCoreThreadTimeOut(true);
    }

    @Test
    public void setMaximumPoolSize() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        pool.setMaximumPoolSize(10);
    }

    @Test
    public void getMaximumPoolSize() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        int m = pool.getMaximumPoolSize();
    }

    @Test
    public void setKeepAliveTime() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        pool.setKeepAliveTime(10, TimeUnit.SECONDS);
    }

    @Test
    public void getKeepAliveTime() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        long k = pool.getKeepAliveTime(TimeUnit.SECONDS);
    }

    @Test
    public void getQueue() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        BlockingQueue<Runnable> queue = pool.getQueue();
    }

    @Test
    public void remove() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        boolean b = pool.remove(() -> System.out.println("1"));
    }

    @Test
    public void purge() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        pool.purge();
    }

    @Test
    public void getPoolSize() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        int p = pool.getPoolSize();
    }

    @Test
    public void getActiveCount() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        int p = pool.getActiveCount();
    }

    @Test
    public void getLargestPoolSize() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        int p = pool.getLargestPoolSize();
    }

    @Test
    public void getTaskCount() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        long p = pool.getTaskCount();
    }

    @Test
    public void getCompletedTaskCount() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        long p = pool.getCompletedTaskCount();
    }

    @Test
    public void toString1() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        String a = pool.toString();
    }

    @Test
    public void CallerRunsPolicy() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Test
    public void AbortPolicy() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    @Test
    public void DiscardPolicy() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
    }

    @Test
    public void DiscardOldestPolicy() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    //--------------------------------------------------

    /**
     * workerCount < corePoolSize，新建一个核心线程执行当前任务
     */
    @Test
    public void poolInitTest() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

        Runnable task = () -> {
            try {
                System.out.printf("thread:%s, pool:%s%n",
                        Thread.currentThread().getName(), pool.toString());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        //workerCount < corePoolSize，新建一个核心线程执行当前任务
        pool.execute(task);
        pool.shutdown();
    }

    /**
     * workerCount > corePoolSize，线程池RUNNING状态，任务加入阻塞队列
     */
    @Test
    public void poolAddQueueTest() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1));

        Runnable task = () -> {
            try {
                System.out.printf("thread:%s, pool:%s%n",
                        Thread.currentThread().getName(), pool.toString());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        pool.execute(task);
        //workerCount > corePoolSize，线程池RUNNING状态，任务加入阻塞队列
        pool.execute(task);
        pool.shutdown();
    }

    /**
     * workerCount > corePoolSize，阻塞队列满了，workerCount < maximumPoolSize 创建非核心线程执行任务
     */
    @Test
    public void poolMaxTest() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1));

        Runnable task = () -> {
            try {
                System.out.printf("thread:%s, pool:%s%n",
                        Thread.currentThread().getName(), pool.toString());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        pool.execute(task);
        pool.execute(task);
        //workerCount > corePoolSize，阻塞队列满了，workerCount < maximumPoolSize 创建非核心线程执行任务
        pool.execute(task);
        pool.shutdown();
    }

    /**
     * workerCount >= maximumPoolSize，阻塞队列满了，不能再创建新线程，采用拒绝策略
     */
    @Test
    public void poolRejectTest() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1),
                new ThreadPoolExecutor.AbortPolicy());

        Runnable task = () -> {
            try {
                System.out.printf("thread:%s, pool:%s%n",
                        Thread.currentThread().getName(), pool.toString());
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        pool.execute(task);
        pool.execute(task);
        //workerCount >= maximumPoolSize，阻塞队列满了，不能再创建新线程，采用拒绝策略
        pool.execute(task);
        pool.shutdown();
    }

    @Test
    public void fut() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2, 3,
                1, TimeUnit.HOURS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        Callable<Integer> t = () -> {
            TimeUnit.SECONDS.sleep(2);
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        };

        FutureTask<Integer> futureTask = new FutureTask<>(t);
        pool.submit(futureTask);

        System.out.println(futureTask.get());
    }

}
