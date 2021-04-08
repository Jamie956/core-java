package com.jamie.concurrency.juc.collections;

import com.jamie.concurrency.ThreadUtil;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class COWArrayList {

    public static void task(List<String> list) {
        for (int i = 1; i < 6; i++) {
            String name = Thread.currentThread().getName() + "  " + i;
//            复制数组
//            Object[] newElements = Arrays.copyOf(elements, len + 1);
//            添加元素
//            newElements[len] = e;
//            设置数组
//            setArray(newElements);
            list.add(name);
        }
    }

    public static void main(String[] args) {
        //new Object[0] 创建空数组
        List<String> list = new CopyOnWriteArrayList<>();

        ThreadUtil.execute(() -> task(list));
        ThreadUtil.execute(() -> task(list));
    }

}
