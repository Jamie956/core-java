package com.jamie.concurrency;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MKTask implements Runnable {
    private final List<String> items;
    private int cur = 0;

    public MKTask(List<String> items) {
        this.items = items;
    }

    @Override
    public void run() {
        for (int j = 0; j < items.size(); j++) {
            try {
                //模拟业务处理耗时
                int s = 1 + (int) (Math.random() * 3);
                TimeUnit.SECONDS.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.cur = j;
        }
    }

    public int getCur() {
        return this.cur + 1;
    }

}
