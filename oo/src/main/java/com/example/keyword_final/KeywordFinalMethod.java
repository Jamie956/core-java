package com.example.keyword_final;

public class KeywordFinalMethod {

}

class ParentF {
    public final void foo() {
    }
}

class ChildF extends ParentF {
    // final 修饰的类不能被继承
    // 'foo()' cannot override 'foo()' in 'com.example.keyword.ParentF'; overridden method is final
//    public void foo() {
//    }
}