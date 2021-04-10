package com.jamie.concurrency.juc;

import com.jamie.concurrency.ThreadUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    public static void main(String[] args) {
        //出现重复数字，有线程安全问题
//        TaskAt taskAt = new TaskAt();
        TaskAt2 taskAt = new TaskAt2();
        for (int i = 0; i < 10; i++) {
            ThreadUtil.execute(taskAt);
        }
    }
}

class TaskAt implements Runnable{
    private int i = 0;
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getI());
    }

    public int getI() {
        /*
        i++包含3个步骤
        temp = i
        i = i+ 1
        i = temp
         */
        return i++;
    }
}

class TaskAt2 implements Runnable{
    private AtomicInteger i = new AtomicInteger();
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getI());
    }

    public int getI() {
        return i.getAndIncrement();
    }
}
