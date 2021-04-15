package com.jamie.strategy;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private static final Map<Integer, Work> MAP = new HashMap<>();

    public static final int WORKA_TYPE = 1;
    public static final int WORKB_TYPE = 2;

    static {
        MAP.put(WORKA_TYPE, new WorkA());
        MAP.put(WORKB_TYPE, new WorkB());
    }

    public void init(int type) {
        MAP.get(type).init();
    }

    public void doHandle(int type) {
        MAP.get(type).doHandle();
    }
}

interface Work {
    void init();

    void doHandle();
}


class WorkA implements Work {
    @Override
    public void init() {

        System.out.println("WorkA 正在初始化");
    }

    @Override
    public void doHandle() {

        System.out.println("WorkA 正在处理中");
    }
}

class WorkB implements Work {
    @Override
    public void init() {
        System.out.println("WorkB 正在初始化");

    }

    @Override
    public void doHandle() {
        System.out.println("WorkB 正在处理中");

    }
}