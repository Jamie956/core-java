package com.jamie.concurrency;

import org.junit.Test;

import java.util.concurrent.*;

public class FutureApiTest {
    Callable<Integer> t = () -> {
        TimeUnit.SECONDS.sleep(2);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    };

    @Test
    public void t() throws ExecutionException, InterruptedException {
        //构造入参 Callable，初始化 state
        FutureTask<Integer> futureTask = new FutureTask<>(t);
        //创建一条线程去执行task
        //由于 FutureTask 实现了接口Runnable，
        //所以线程执行的run 相当于执行重写方法 futureTask.run()
        new Thread(futureTask).start();

        System.out.println("do something...");
        //获取执行结果
        Integer ret = futureTask.get();
        System.out.println("result：" + ret);
    }

    /**
     * FutureTask未启动，状态为NEW，执行get会阻塞
     */
    @Test
    public void t2() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(t);

        //futureTask 还没run
        //state 为 NEW 时，调 LockSupport.park 阻塞
        Integer ret = futureTask.get();
        System.out.println("result：" + ret);
    }

    /**
     * FutureTask未启动，状态为NEW，执行get会阻塞，使用cancel 中断
     */
    @Test
    public void t3() throws InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(t);
        //创建一条线程去执行 get
        Thread t1 = new Thread(() -> {
            try {
                Integer ret = futureTask.get();
                System.out.println("result：" + ret);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread.sleep(5000);

        //中断任务，可以中断 get 阻塞
        futureTask.cancel(false);
        //主线程等待t1 线程执行完
        t1.join();
    }

    /**
     * FutureTask启动后，执行get不会阻塞，使用cancel 中断
     */
    @Test
    public void t4() throws InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(t);

        //创建一条线程执行任务并获取执行结果
        Thread t1 = new Thread(() -> {
            try {
                futureTask.run();
                Integer ret = futureTask.get();
                System.out.println("result：" + ret);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread.sleep(5000);
        futureTask.cancel(false);

        t1.join();
    }

    @Test
    public void t5() throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<Integer> futureTask = new FutureTask<>(t);

        //futureTask 还没run
        //state 为 NEW 时，调 LockSupport.park 阻塞，阻塞3秒后中断
        //如果任务超过3秒都还没完成，状态还是NEW，就抛出异常
        Integer ret = futureTask.get(3, TimeUnit.SECONDS);
        System.out.println("result：" + ret);
    }
}