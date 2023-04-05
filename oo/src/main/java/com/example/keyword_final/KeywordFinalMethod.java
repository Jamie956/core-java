package com.example.keyword_final;

// 父类 final 方法
public class KeywordFinalMethod extends KeywordFinalMethodParent{
    // 父类 final 方法不能被重写
//    public void foo() {}
}

class KeywordFinalMethodParent {
    public final void foo() {
    }
}
