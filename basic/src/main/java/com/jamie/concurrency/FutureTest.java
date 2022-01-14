package com.jamie.concurrency;

import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task t = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(t);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(futureTask);


        System.out.println("wait");
        //get 阻塞
        System.out.println("result：" + futureTask.get());
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws InterruptedException {
            TimeUnit.SECONDS.sleep(2);
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }
}