package com.jamie.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FuTask fuTask = new FuTask();
        FutureTask<Integer> futureTask = new FutureTask<>(fuTask);
        ThreadUtil.execute(futureTask);

        System.out.println("等待执行");
        Integer sum = futureTask.get();
        System.out.println("执行结果："+sum);
    }
}

class FuTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}