package com.jamie.java8;

public class LambdaTest {
    public static void main(String[] args) {
        ILike like = () -> {
            System.out.println("hi");
        };

        like.lam();
    }
}

//函数式接口
interface ILike {
    //只允许一个方法
    void lam();
}
