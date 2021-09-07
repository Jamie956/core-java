package com.jamie;

public class LambdaTest {
    public static void main(String[] args) {
        LambdaInterface like = () -> System.out.println("hi");
        like.lam();
    }
}

//函数式接口
interface LambdaInterface {
    //只允许一个方法
    void lam();
}
