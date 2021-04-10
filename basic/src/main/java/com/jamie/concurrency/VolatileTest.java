package com.jamie.concurrency;

import java.util.concurrent.TimeUnit;

public class VolatileTest {

    public static void main(String[] args) {
        Task task = new Task();
        ThreadUtil.execute(task);

        while (true) {
            //第一次进去循环时，flag还没设成true，当task 线程把flag 改成true，main 线程并不能获取最新的flag的值
            if (task.isFlag()) {
                System.out.println("--------------");
                break;
            }
        }
    }
}

class Task implements Runnable{

    private boolean flag = false;
    //volatile 使变量在内存可见
//    private volatile boolean flag = false;
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println(isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
