package com.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Task implements Runnable {
    protected int counter = 0;
    protected List<String> items = new ArrayList<>();

    /**
     * 提交需要处理的数据给任务
     */
    public void commitData(List<String> data) {
        items.addAll(data);
    }

    /**
     * 任务进度
     */
    public float getProcess() {
        return (float) this.counter / items.size();
    }
}
