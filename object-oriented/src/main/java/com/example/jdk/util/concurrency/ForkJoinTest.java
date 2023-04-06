package com.example.jdk.util.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {

    static class Sum extends RecursiveTask<Long> {
        private static final int THRESHOLD = 10;
        private final long from;
        private final long to;

        Sum(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            if ((to - from) < THRESHOLD) {
                for (long i = from; i <= to; i++) {
                    sum = sum + i;
                }
            } else {
                long mid = (from + to) >>> 1;

                Sum left = new Sum(from, mid);
                left.fork();
                Sum right = new Sum(mid + 1, to);
                right.fork();

                sum = left.join() + right.join();
            }
            return sum;
        }
    }

    public static void main(String[] args) throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Long> future = forkJoinPool.submit(new Sum(1, 10000));
        //Sum：50005000
        System.out.println("Sum：" + future.get());
    }
}