package com.jamie.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * java8 consumer lambda 实现策略模式
 */
public class Query {
    public Map<String, Wrapper> map = new HashMap<>();

    public Query() {
        map.put("lt", () -> " < ");
        map.put("eq", () -> " = ");
    }

    public String getWrapper(List<String> queryItems, String sql) {
        for (String queryItem : queryItems) {
            sql = sql + map.get(queryItem).doHandle();
        }
        return sql;
    }

}

@FunctionalInterface
interface Wrapper {
    String doHandle();
}