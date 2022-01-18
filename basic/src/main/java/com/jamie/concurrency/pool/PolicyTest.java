package com.jamie.concurrency.pool;

import java.util.concurrent.*;

public class PolicyTest {

    public static void main(String[] args) {
        new PolicyTest().start(new ThreadPoolExecutor.DiscardPolicy());
//        new PolicyTest().start(new ThreadPoolExecutor.DiscardOldestPolicy());
//        new PolicyTest().start(new ThreadPoolExecutor.AbortPolicy());
//        new PolicyTest().start(new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void start(RejectedExecutionHandler handler) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(), handler);
        for (int i = 0; i < 10; i++) {
            pool.execute(new Work("t" + i));
        }
        pool.shutdown();
    }

    static class Work implements Runnable {
        private String name;

        Work(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(name);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

