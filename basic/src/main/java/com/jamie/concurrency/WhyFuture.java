package com.jamie.concurrency;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 方法中可能会调用到多个服务/方法，且这些服务/方法之间是互相独立的，
 * 不存在先后关系。在高并发场景下，如果执行比较耗时，可以考虑多线程异步的方式调用。
 */
public class WhyFuture {
    /**
     * 不使用FutureTask
     */
    @Test
    public void testNotUseFutureTask() {
        UserApi userApi = new UserApi();

        long userId = 12;
        long startTime = System.currentTimeMillis();

        // 获取用户基本信息
        String userInfo = userApi.queryUserInfo(userId);
        // 获取用户地址
        String userAddress = userApi.queryUserAddress(userId);

        System.out.println("userInfo: " + userInfo);
        System.out.println("userAddress: " + userAddress);
        System.err.println("testNotUseFutureTask 耗时：" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 使用FutureTask
     */
    @Test
    public void testUseFutureTask() throws ExecutionException, InterruptedException {
        UserApi userApi = new UserApi();

        long userId = 12;
        long startTime = System.currentTimeMillis();

        Callable<String> userInfoCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return userApi.queryUserInfo(userId);
            }
        };
        Callable<String> userAddressCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return userApi.queryUserAddress(userId);
            }
        };
        FutureTask<String> userInfoFutureTask = new FutureTask<>(userInfoCallable);
        FutureTask<String> userAddressFutureTask = new FutureTask<>(userAddressCallable);

        new Thread(userInfoFutureTask).start();
        new Thread(userAddressFutureTask).start();

        String userInfo = userInfoFutureTask.get();
        String userAddress = userAddressFutureTask.get();

        System.out.println("userInfo: " + userInfo);
        System.out.println("userAddress: " + userAddress);
        System.err.println("testUseFutureTask 耗时：" + (System.currentTimeMillis() - startTime));
    }

    static class UserApi {

        /**
         * 查询用户基本信息，模拟耗时150ms
         */
        public String queryUserInfo(long userId) {
            String userInfo = "userInfo: " + userId;

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
        public String queryUserAddress(long userId) {
            String userAddress = "userAddress: " + userId;

            try {
                TimeUnit.MILLISECONDS.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return userAddress;
        }
    }
}
