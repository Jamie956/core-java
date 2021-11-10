package com.jamie.task;

import org.junit.Test;

import java.util.List;

public class DemoMain {
    /**
     * 将任务切分，由多线程处理，并打印处理进度
     */
    @Test
    public void handleAll() throws InterruptedException {
        //模拟数据库取数据
        DataSimulation listFactory = new DataSimulation();

        TaskExecutor executor = new TaskExecutor();
        List<String> data = listFactory.getNext();

        while (data != null) {
            System.out.println(String.format("第%s页", listFactory.getCurPage()));
            executor.sliceDataAndExecute(data, 5, DataTask::new);
            data = listFactory.getNext();
        }
        executor.checkProcess();
    }
}
