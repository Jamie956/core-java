package com.example.jdk.util.concurrency;

import org.junit.Test;

import java.util.concurrent.*;

public class FutureTaskTest {
    @Test
    public void cons() {
        Callable<Integer> c = () -> {
            int a = 0;
            for (int i = 0; i < 100; i++) {
                a += i;
            }
            return a;
        };

        FutureTask<Integer> ft = new FutureTask<>(c);
    }

    @Test
    public void cons2() {
        Runnable r = () -> {
            int a = 0;
            for (int i = 0; i < 100; i++) {
                a += i;
            }
        };

        FutureTask<Integer> ft = new FutureTask<>(r, 1000);
    }

    @Test
    public void isCancelled() {
        Callable<Integer> c = () -> {
            int a = 0;
            for (int i = 0; i < 100; i++) {
                a += i;
            }
            return a;
        };

        FutureTask<Integer> ft = new FutureTask<>(c);
        ft.cancel(true);
        boolean b = ft.isCancelled();
    }

    @Test
    public void isDone() throws ExecutionException, InterruptedException {
        Callable<Integer> c = () -> {
            int a = 0;
            for (int i = 0; i < 5; i++) {
                a += i;
            }
            return a;
        };

        FutureTask<Integer> ft = new FutureTask<>(c);
        Integer i = ft.get();
        boolean b = ft.isDone();
    }

    @Test
    public void cancel() {
        Callable<Integer> c = () -> {
            int a = 0;
            for (int i = 0; i < 100; i++) {
                a += i;
            }
            return a;
        };

        FutureTask<Integer> ft = new FutureTask<>(c);
        ft.cancel(true);
    }

    @Test
    public void get() throws ExecutionException, InterruptedException {
        Callable<Integer> c = () -> {
            int a = 0;
            for (int i = 0; i < 100; i++) {
                a += i;
            }
            return a;
        };

        FutureTask<Integer> ft = new FutureTask<>(c);
        new Thread(ft).start();
        Integer result = ft.get();
    }

    @Test
    public void get2() throws ExecutionException, InterruptedException, TimeoutException {
        Callable<Integer> c = () -> {
            int a = 0;
            for (int i = 0; i < 100; i++) {
                a += i;
            }
            return a;
        };

        FutureTask<Integer> ft = new FutureTask<>(c);
        Thread t = new Thread(ft);
        t.start();

        Integer result = ft.get(5, TimeUnit.SECONDS);
        System.out.println(result);
    }

    @Test
    public void run() throws ExecutionException, InterruptedException {
        Callable<Integer> c = () -> {
            int a = 0;
            for (int i = 0; i < 100; i++) {
                a += i;
            }
            return a;
        };

        FutureTask<Integer> ft = new FutureTask<>(c);
        ft.run();
        Integer r = ft.get();
        System.out.println(r);
    }

    @Test
    public void run2() throws ExecutionException, InterruptedException {
        Callable<Integer> c = () -> {
            int a = 0 / 0;
            return 0;
        };

        FutureTask<Integer> ft = new FutureTask<>(c);
        ft.run();
        Integer r = ft.get();
        System.out.println(r);
    }

    //------------------------- 场景模拟 -------------------------

    /**
     * 方法中可能会调用到多个服务/方法，且这些服务/方法之间是互相独立的，
     * 不存在先后关系。在高并发场景下，如果执行比较耗时，可以考虑多线程异步的方式调用。
     */
    @Test
    public void testUseFutureTask() throws ExecutionException, InterruptedException {
        UserApi userApi = new UserApi();

        String name = "tom";
        String ad = "GZ";
        long startTime = System.currentTimeMillis();

        Callable<String> c1 = () -> userApi.queryUserInfo(name);
        Callable<String> c2 = () -> userApi.queryUserAddress(ad);

        FutureTask<String> f1 = new FutureTask<>(c1);
        FutureTask<String> f2 = new FutureTask<>(c2);

        //多线程执行任务
        new Thread(f1).start();
        new Thread(f2).start();

        String userInfo = f1.get();
        String userAddress = f2.get();

        System.out.println("userInfo: " + userInfo);
        System.out.println("userAddress: " + userAddress);
        //耗时少于350
        System.err.println("testUseFutureTask 耗时：" + (System.currentTimeMillis() - startTime));
    }

    static class UserApi {

        /**
         * 查询用户基本信息，模拟耗时150ms
         */
        public String queryUserInfo(String name) {
            String userInfo = "userInfo: " + name;

            try {
                TimeUnit.MILLISECONDS.sleep(150L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return userInfo;
        }

        /**
         * 查询用户地址，模拟耗时200ms
         */
        public String queryUserAddress(String ad) {
            String userAddress = "userAddress: " + ad;

            try {
                TimeUnit.MILLISECONDS.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return userAddress;
        }
    }
}
