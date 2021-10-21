package com.jamie.strategy;

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

}

@FunctionalInterface
interface Wrapper {
    String doHandle();
}