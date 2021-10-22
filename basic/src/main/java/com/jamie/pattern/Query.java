package com.jamie.pattern;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query {
    public Map<String, Wrapper> map;

    public Query() {
        map = new HashMap<>();
        map.put("lt", () -> " < ");
        map.put("eq", () -> " = ");
    }

    public String getWrapper(List<String> queryItems, String sql) {
        for (String queryItem : queryItems) {
            sql = sql + map.get(queryItem).doHandle();
        }
        return sql;
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

@FunctionalInterface
interface Wrapper {
    String doHandle();
}