package com.jamie.concurrency;

import com.jamie.ThreadUtil;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MultiThread {
    List<MKTask> taskList = new ArrayList<>();

    /**
     * 将任务切分，由多线程处理，并打印处理进度
     */
    @Test
    public void handleAll() throws InterruptedException {
        ListFactory listFactory = new ListFactory();
        int total = listFactory.getSize();

        while (true) {
            //获取翻页数据
            List<String> data = listFactory.getNext();
            //模拟取数据耗时
            TimeUnit.SECONDS.sleep(1);
            if (data == null) {
                break;
            }

            //每个线程处理 5 条
            int threadCount = 5;
            System.out.println("当前页" + listFactory.getCurPage());
            handlePageDate(data, threadCount);
        }

        //计算任务进度
        while (true) {
            TimeUnit.SECONDS.sleep(2);
            //每2秒打印一次各个线程的处理进度
            int sum = 0;
            for (MKTask mkTask : taskList) {
                int cur = mkTask.getCur();
                sum = sum + cur;
            }
            DecimalFormat dF = new DecimalFormat("0.00");
            float f = (float) sum / total;

            if (f == 1.0) {
                System.out.println("全部任务完成");
                break;
            }
            System.out.println("任务处理进度 " + dF.format(f * 100) + "%");
        }
    }

    public void handlePageDate(List<String> data, int threadCount) {
        int pageSize = data.size();

        //分成 segment 份，需要 segment 个线程处理
        int segment = (int) Math.ceil((double) pageSize / threadCount);

        System.out.println("当前页数据 " + data + " 一共有 " + pageSize + " 条, 每个线程处理 " + threadCount + " 条数据, 需要 " + segment + " 个线程处理");

        for (int i = 0; i < segment; i++) {
            int start = i * threadCount;
            int end = (i == segment - 1) ? pageSize : (start + threadCount);

            List<String> subList = data.subList(start, end);
            MKTask mkTask = new MKTask(subList);
            taskList.add(mkTask);
            System.out.println(start + " - " + end + " 范围数据 " + subList + " 交给一条线程处理");
            ThreadUtil.execute(mkTask);
        }
    }
}

