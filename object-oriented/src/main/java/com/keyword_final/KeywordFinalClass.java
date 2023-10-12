package com.keyword_final;

import org.junit.Assert;
import org.junit.Test;

// final 修饰的类
public final class KeywordFinalClass {
    // final 类的非 final 全局变量可以被修改，没有被隐式修饰为 final
    public String s = "aa";

    // final 类的非 final 方法 隐式指定为 final
    public void foo() {}

    @Test
    public void finalClassVarTest() {
        KeywordFinalClass o = new KeywordFinalClass();
        o.s = "update";
        Assert.assertEquals("update", o.s);
    }
}
