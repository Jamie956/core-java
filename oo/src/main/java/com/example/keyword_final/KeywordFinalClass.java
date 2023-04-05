package com.example.keyword_final;

// final 修饰的类
public final class KeywordFinalClass {
    // final 类的非final 全局变量可以修改，没有被隐式修饰为final
    public String s = "aa";

    // final 类的非final 方法 隐式指定为 final
    public void foo() {}

    public static void main(String[] args) {
        KeywordFinalClass o = new KeywordFinalClass();
        o.s = "update";
        System.out.println(o.s);
    }
}
