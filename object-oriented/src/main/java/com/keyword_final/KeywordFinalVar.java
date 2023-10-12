package com.keyword_final;

import org.junit.Assert;
import org.junit.Test;

public class KeywordFinalVar {
    // final 修饰变量
    public final String a = "a";
    public final Object[] arr = {1,2};

    @Test
    public void finalVar() {
        KeywordFinalVar o = new KeywordFinalVar();
        // final 修饰的基本数据类型不可修改
//        o.a = "aaa";

        // final 修饰的引用类型，对象堆内存的值可变
        Assert.assertEquals(1, o.arr[0]);
        Assert.assertEquals(2, o.arr[1]);
        o.arr[0] = 3;
//        System.out.println(o.arr[0]);
        Assert.assertEquals(3, o.arr[0]);

        // final 修饰的引用类型，地址不可变
        Object[] arr2 = {5,6};
//        o.arr = arr2;
    }
}
