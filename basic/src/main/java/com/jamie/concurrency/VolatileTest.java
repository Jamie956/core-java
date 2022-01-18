package com.jamie.concurrency;

import java.util.concurrent.TimeUnit;

public class VolatileTest {
    static class MyThread extends Thread{
//            private volatile boolean flag = false;
        private boolean flag = false;

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setFlag(true);
            System.out.println(Thread.currentThread().getName() + "修改了flag值");
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }

    public static void main(String[] args) {
        //新建一条线程修改flag，如果flag 使用volatile 修饰，其他线程也可以看到 flag的更新
        MyThread t = new MyThread();
        t.start();

        while (true) {
            if (t.isFlag()) {
                System.out.println(Thread.currentThread().getName() + "读取的flag 被其他线程修改了");
                break;
            }
        }
    }
}