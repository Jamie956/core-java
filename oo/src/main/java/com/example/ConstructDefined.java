package com.example;

public class ConstructDefined {
    private int i;
    private int j;

    public ConstructDefined(int i) {
        this.i = i;
    }

    public ConstructDefined(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public static void main(String[] args) {
        // 报错，定义了有参构造方法时，不提供无参构造方法
//        new ConstructDefined();
    }
}
