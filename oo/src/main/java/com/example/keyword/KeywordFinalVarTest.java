package com.example.keyword;

public class KeywordFinalVarTest {
    public static void main(String[] args) {
        KeywordFinalVar o = new KeywordFinalVar();
        //   final 修饰的基本数据类型，值不可变
        // Cannot assign a value to final variable 'a'
//        o.a = "aaa";

        //   final 修饰的引用类型，地址不可变，对象堆内存的值可变
        o.apple.s = "update";
        System.out.println(o.apple.s);
    }
}
