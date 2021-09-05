package com.jamie;

import org.junit.Test;

public class InitOrder {
    /**
     * 执行顺序
     * 1父类静态代码块
     * 2子类静态代码块
     * 3父类代码块
     * 5父类构造函数
     * 4子类代码块
     * 6子类构造函数
     */
    @Test
    public void testOrder() {
        new InitOrderB();
    }
}

class InitOrderA {
    static {
        System.out.println("父类静态代码块");
    }

    {
        System.out.println("父类代码块");
    }

    InitOrderA() {
        System.out.println("父类构造函数");
    }
}

class InitOrderB extends InitOrderA {
    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类代码块");
    }

    public InitOrderB() {
        System.out.println("子类构造函数");
    }
}
