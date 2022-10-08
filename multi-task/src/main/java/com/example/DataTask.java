package com.example;

import java.util.concurrent.TimeUnit;

public class DataTask extends Task {
    @Override
    public void run() {
        for (String item : items) {
            try {
                //模拟业务处理耗时
                int s = 1 + (int) (Math.random() * 3);
                TimeUnit.SECONDS.sleep(s);
                //已处理一条数据
                System.out.println(Thread.currentThread().getName() + " 已处理数据" + item);
                this.counter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}