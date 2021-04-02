package com.jamie.concurrency;

import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Zjm
 * @Date: 2021/4/2 18:11
 */
public class MyTask implements Runnable {
    private List<String> list;
    private int start;
    private int end;
    private int cur;

    public MyTask(List<String> list, int start, int end) {
        this.list = list;
        this.start = start;
        this.end = end;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("线程 " + Thread.currentThread().getName() + "处理 " + start + " - " + end + " 的数据 " + list);
        for (int j = 0; j < list.size(); j++) {
            //模拟耗时任务
            TimeUnit.SECONDS.sleep(3);
            System.out.println("线程 " + Thread.currentThread().getName() + " 当前处理" + list.get(j) + ", 处理进度 " + j + "/" + (list.size() - 1));
            cur = j;
        }
    }
}
