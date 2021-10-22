package com.jamie.pattern;

import java.util.HashMap;
import java.util.Map;

public class Context {
    public static void main(String[] args) {
        Context context = new Context();
        context.init(Context.WORKA_TYPE);
        context.doHandle(Context.WORKA_TYPE);
    }

    private static final Map<Integer, Work> MAP;

    public static final int WORKA_TYPE = 1;
    public static final int WORKB_TYPE = 2;

    static {
        //静态代码块初始映射对象
        MAP = new HashMap<>();
        MAP.put(WORKA_TYPE, new WorkA());
        MAP.put(WORKB_TYPE, new WorkB());
    }

    //获取映射对象并调取对象方法（方法来自实现的同一接口）
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