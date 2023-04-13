package com.example;

import org.openjdk.jol.info.ClassLayout;

public class JolDemo {
    public static void main(String[] args) {
        int obj = 1;
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        //查看对象外部信息
//        System.out.println(GraphLayout.parseInstance(obj).toPrintable());
        //获取对象总大小
//        System.out.println(GraphLayout.parseInstance(obj).totalSize());
    }
}