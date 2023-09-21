package com.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericExtends {

    public static void main(String[] args) {
        GenericExtends o = new GenericExtends();
        o.fromArrayToList(new Integer("1"));

        List<Number> dest = new ArrayList<>();
        List<Integer> src = new ArrayList<>();
        copy(dest, src);
    }

    // (T a) 表示参数类型 T 决定返回类型 List<T> 和 T 与 Number 的父子关系
    // <T extends Number> List<T> 表示 T 类型必须是 Number 的子类
    public <T extends Number> List<T> fromArrayToList(T a) {
        return null;
    }

    // List<? super G> 表示参数类型是泛型 G 的超类 G -> dest
    // List<? extends G> 表示参数类型是泛型 G 的子类 src -> G
    // 组合起来就是 src -> G -> dest
    public static <G> void copy(List<? super G> dest, List<? extends G> src) {
    }
}