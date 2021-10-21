package com.jamie.strategy;

import org.junit.Test;

import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {
        Context context = new Context();
        context.init(Context.WORKA_TYPE);
        context.doHandle(Context.WORKA_TYPE);
    }

    @Test
    public void queryTest1() {
        Query query = new Query();
        String sql = query.getWrapper(Arrays.asList("lt", "eq"), "select ");
        System.out.println(sql);
    }

    @Test
    public void queryTest2() {
        Query query = new Query();
        query.map.put("gt", () -> " > ");
        String sql = query.getWrapper(Arrays.asList("gt", "lt"), "select ");
        System.out.println(sql);
    }
}