package com.example.keyword_final;

// final 修饰变量
public class KeywordFinalVar {
    public final String a = "a";
    public final Object[] arr = {1,2};

    public static void main(String[] args) {
        KeywordFinalVar o = new KeywordFinalVar();
        // final 修饰的基本数据类型不可修改
//        o.a = "aaa";

        // final 修饰的引用类型，对象堆内存的值可变
        o.arr[0] = 3;
        System.out.println(o.arr[0]);

        // final 修饰的引用类型，地址不可变
        Object[] arr2 = {5,6};
//        o.arr = arr2;
    }
}
