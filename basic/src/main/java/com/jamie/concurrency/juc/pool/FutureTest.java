package com.jamie.concurrency.juc.pool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTest {
    @Test
    public void futureTaskTestPool() {
        try {
            FutureTask<Integer> futureTask = new FutureTask<>(() -> 1);
            ExecutorService pool = Executors.newSingleThreadExecutor();
            pool.submit(futureTask);
            int a = futureTask.get();
            pool.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    /**
     * future task
     */
    @Test
    public void futureTaskTest() {
        try {
            FutureTask<Integer> futureTask = new FutureTask<>(() -> 1);
            new Thread(futureTask).start();
            int a = futureTask.get();
            System.out.println();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
