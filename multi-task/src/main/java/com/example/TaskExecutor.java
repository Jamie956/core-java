package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class TaskExecutor {
    /**
     * 任务集，包括全部任务
     */
    private List<Task> taskList = new ArrayList<>();

    /**
     * 多线程处理处理的数据集
     *
     * @param data                    需要处理的数据集
     * @param handleDataSizePerThread 每条线程处理的数据大小
     */
    public void sliceDataAndExecute(List<String> data, int handleDataSizePerThread, Supplier<Task> taskSupplier) {
        int dataSize = data.size();
        //需要threadCountToHandle 个线程处理
        int threadCountToHandle = (int) Math.ceil((double) dataSize / handleDataSizePerThread);
        System.out.println(String.format("页数据 %s 一共有 %S 条, 每个线程处理 %s 条数据, 需要 %s 个线程处理", data, dataSize, handleDataSizePerThread, threadCountToHandle));
        for (int i = 0; i < threadCountToHandle; i++) {
            int start = i * handleDataSizePerThread;
            int end = (i == threadCountToHandle - 1) ? dataSize : (start + handleDataSizePerThread);
            //切分页数据
            List<String> subList = data.subList(start, end);

            //处理数据的任务
            Task task = taskSupplier.get();
            task.commitData(subList);
            taskList.add(task);

            System.out.println(String.format("数据切分start=%s, end=%s，分派一条线程处理数据集 %s", start, end, subList));
//            ThreadUtil.execute(task);
            ExecutorService pool = Executors.newFixedThreadPool(10);
            pool.execute(task);
        }
    }

    /**
     * 计算任务进度
     */
    public void checkProcess() throws InterruptedException {
        while (true) {
            TimeUnit.SECONDS.sleep(2);
            //每2秒打印一次处理进度
            Float sum = taskList.stream().map(Task::getProcess).reduce(Float::sum).orElse(0f);
            float totalProcess = sum / taskList.size();
            System.out.println("任务处理进度  " + totalProcess * 100 + "%");
            if (totalProcess == 1.0) {
                System.out.println("全部任务处理完成");
                break;
            }
        }
    }
}
