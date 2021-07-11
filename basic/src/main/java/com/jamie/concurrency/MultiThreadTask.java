package com.jamie.concurrency;

import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MultiThreadTask {
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

class MKTask implements Runnable {
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

class ListFactory {

    private List<String> data;
    private int curPage = 0;
    private int pageSize = 20;

    public ListFactory() {
        String[] arr = new String[]{"9", "1", "2", "1", "3", "5", "8", "3", "9", "6", "8", "7", "5", "5", "1", "1", "5", "4", "9", "4", "2", "7", "8", "8", "5", "1", "4", "3", "8", "1", "2", "4", "2", "4", "4", "8", "8", "4", "4", "5", "3", "3", "6", "4", "7", "6", "5", "4", "1", "8", "4", "3", "6", "7", "7", "4", "5", "3", "9", "9", "5", "5", "6", "1", "4", "1", "9", "3", "5", "1", "1", "7", "9", "4", "9", "2", "8", "7", "6", "6", "4", "1", "6", "6", "9", "1", "5", "2", "1", "1", "8", "1", "6", "3", "1", "8", "4"};
        this.data = Arrays.asList(arr);
        System.out.println("全部数据 " + this.data);
    }

    public List<String> getNext() {
        int start = curPage * pageSize;

        //数据取完了
        if (start > data.size()) {
            return null;
        }
        int end = Math.min((start + pageSize), data.size());

        //下一页
        curPage++;
        return this.data.subList(start, end);
    }

    public int getSize() {
        return this.data.size();
    }

    public int getCurPage() {
        return this.curPage;
    }

    public static void main(String[] args) {
        ListFactory listFactory = new ListFactory();
        for (int i = 0; i < 10; i++) {
            List<String> data = listFactory.getNext();
            System.out.println(data);
        }
    }
}
