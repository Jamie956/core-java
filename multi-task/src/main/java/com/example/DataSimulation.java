package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 模拟数据分页查询
 */
public class DataSimulation {

    private List<String> data;
    private int curPage = 0;
    private int pageSize = 20;

    public DataSimulation() {
//        String[] arr = new String[]{"9", "1", "2", "1", "3", "5", "8", "3", "9", "6", "8", "7", "5", "5", "1", "1", "5", "4", "9", "4", "2", "7", "8", "8", "5", "1", "4", "3", "8", "1", "2", "4", "2", "4", "4", "8", "8", "4", "4", "5", "3", "3", "6", "4", "7", "6", "5", "4", "1", "8", "4", "3", "6", "7", "7", "4", "5", "3", "9", "9", "5", "5", "6", "1", "4", "1", "9", "3", "5", "1", "1", "7", "9", "4", "9", "2", "8", "7", "6", "6", "4", "1", "6", "6", "9", "1", "5", "2", "1", "1", "8", "1", "6", "3", "1", "8", "4"};
        String[] arr = new String[]{"9", "1", "2", "1", "3", "5", "8"};
        this.data = Arrays.asList(arr);
    }

    public List<String> getNext() throws InterruptedException {
        //模拟取数据耗时
        TimeUnit.SECONDS.sleep(1);

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
}
