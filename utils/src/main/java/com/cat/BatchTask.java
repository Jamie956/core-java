package com.cat;

import java.util.ArrayList;
import java.util.List;

public class BatchTask {
    public static void main(String[] args) {

        List<String> items = new ArrayList<>();
        int dataSize = 10082;
        int batchSize = 500;

        for (int i = 0; i < dataSize; i++) {
            items.add("a" + i);
            if (items.size() % batchSize == 0) {
                System.out.println("提交一批数据:" + items);
                System.out.println("提交一批数据:" + items.size());
                items.clear();
            } else if (i == dataSize - 1) {
                System.out.println("提交一批数据:" + items);
                System.out.println("提交一批数据:" + items.size());
                items.clear();
            }

        }
    }
}
