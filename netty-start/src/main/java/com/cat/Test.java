package com.cat;

import io.netty.util.NettyRuntime;

public class Test {
    public static void main(String[] args) {
        //netty 方法获取集器处理器个数
        System.out.println(NettyRuntime.availableProcessors());
    }
}
