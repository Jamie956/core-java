package com.keyword_final;

public class KeywordFinalMethod extends KeywordFinalMethodParent{
    // 不能被重写父类 final 方法
//    public void foo() {}
}

class KeywordFinalMethodParent {
    // 父类 final 方法
    public final void foo() {
    }
}
