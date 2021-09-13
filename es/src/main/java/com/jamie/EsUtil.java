package com.jamie;

import lombok.experimental.UtilityClass;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.*;

/**
 * @author ZJM
 * @date 2021/9/7 16:06
 */
@UtilityClass
public class EsUtil {
    /**
     * terms 精确查询
     */
    public static QueryBuilder terms(String name, String... values) {
        return QueryBuilders.termsQuery(name, values);
    }

    /**
     * 多字段匹配
     */
    public static QueryBuilder multiMatch(Object text, String... fieldNames) {
        return QueryBuilders.multiMatchQuery(text, fieldNames);
    }

    /**
     * 分词查询
     */
    public static QueryBuilder match(String name, Object text) {
        return QueryBuilders.matchQuery(name, text);
    }

    /**
     * 范围查询
     */
    public static QueryBuilder range(String name, Object from, Object to) {
        return QueryBuilders.rangeQuery(name).gte(from).lte(to);
    }

    /**
     * 匹配全部
     */
    public static QueryBuilder matchAll() {
        return QueryBuilders.matchAllQuery();
    }

    /**
     * 匹配表达式
     * QueryBuilders.regexpQuery("name", "zha[a-z]*")
     */
    public static QueryBuilder regexp(String name, String regexp) {
        return QueryBuilders.regexpQuery(name, regexp);
    }

    /**
     * 多id查询
     */
    public static QueryBuilder idsQuery(String[] ids) {
        return QueryBuilders.idsQuery().addIds(ids);
    }

    /**
     * name=value1 | name=value2 | name=value3
     */
    public void boolQuery(String name, String[] values) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        for (String value : values) {
            boolQuery.should(QueryBuilders.termQuery(name, value));
        }
    }

    /**
     * field1=value and field2=value and field3=value
     */
    public static QueryBuilder multiShould(Object text, String... fieldNames) {
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        return bool.should(QueryBuilders.multiMatchQuery(text, fieldNames));
    }

    public static BoolQueryBuilder filterTerm(Map<String, Object> data) {
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        data.forEach((k,v) -> {
            bool.filter(QueryBuilders.termQuery(k, v));
        });
        return bool;
    }

    public static AggregationBuilder aggExtends(String name, String field) {
        return AggregationBuilders.extendedStats(name).field(field);
    }

    public static AggregationBuilder aggRange(String name, String field, int from, int to) {
        return AggregationBuilders.range(name).field(field).addRange(from, to);
    }

    public static AggregationBuilder aggTerm(String name, String field, int size) {
        return AggregationBuilders.terms(name).field(field).size(size);
    }

    public static AggregationBuilder aggTopHits(String name, String field, int size) {
        return AggregationBuilders.topHits(name).sort(field, SortOrder.DESC).size(size);
    }

}
