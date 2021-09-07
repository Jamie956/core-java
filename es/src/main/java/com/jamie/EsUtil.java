package com.jamie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.experimental.UtilityClass;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author ZJM
 * @date 2021/9/7 16:06
 */
@UtilityClass
public class EsUtil {
    private static RestHighLevelClient client;
    private static final String ES_HOST = "localhost";
    private static final Integer ES_PORT = 9200;

    static {
        HttpHost http = new HttpHost(ES_HOST, ES_PORT);
        RestClientBuilder builder = RestClient.builder(http);
        client =  new RestHighLevelClient(builder);
    }

    public static RestHighLevelClient getClient() {
        return client;
    }

    /**
     * 判断索引是否存在
     * @param indices 索引名
     * @return 是否存在
     */
    public static boolean indexExist(String indices) throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest().indices(indices);
        return client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 删除索引
     * @param indices 索引名
     * @return 是否删除成功
     */
    public static boolean deleteIndex(String indices) throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest().indices(indices);
        AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        return delete.isAcknowledged();
    }

    /**
     * 批量创建文档
     * @param indices 索引名
     * @param type 类型
     * @param list 文档对象
     */
    public static void bulkCreateDoc(String indices, String type, List<Object> list) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        list.forEach(e -> bulkRequest.add(new IndexRequest(indices, type).source(JSONObject.toJSONString(e), XContentType.JSON)));
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    /**
     * 批量创建文档
     * @param indices 索引名
     * @param type 类型
     * @param map 文档对象
     */
    public static void bulkCreateDoc(String indices, String type, Map<String, Object> map) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        map.forEach((k,v) -> bulkRequest.add(new IndexRequest(indices, type, k).source(JSONObject.toJSONString(v), XContentType.JSON)));
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    /**
     * 批量删除文档
     * @param indices 索引名
     * @param type 类型
     * @param ids id集合
     */
    public static void bulkDeleteDoc(String indices, String type, List<String> ids) throws IOException {
        BulkRequest request = new BulkRequest();
        ids.forEach(e -> request.add(new DeleteRequest(indices, type, e)));
        client.bulk(request, RequestOptions.DEFAULT);
    }

    /**
     * 创建文档
     * @param indices 索引名
     * @param type 类型
     * @param o 文档对象
     */
    public static boolean createDoc(String indices, String type, Object o) throws IOException {
        IndexRequest request = new IndexRequest(indices, type).source(JSONObject.toJSONString(o), XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        return "created".equals(response.getResult().getLowercase());
    }

    /**
     * 更新文档
     */
    public static boolean updateDoc(String indices, String type, String id, Map<String, String> map) throws IOException {
        UpdateRequest request = new UpdateRequest(indices, type, id).doc(map);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        return "updated".equals(response.getResult().getLowercase());
    }

    /**
     * 删除文档
     */
    public static boolean deleteDoc(String indices, String type, String id) throws IOException {
        DeleteRequest request = new DeleteRequest(indices, type, id);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        return "deleted".equals(response.getResult().getLowercase());
    }

    public void search(String indices, String type, SearchSourceBuilder builder) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(indices).types(type).source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    public <T> List<T> search(String indices, String type, QueryBuilder query, Class<T> clazz) throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(query);

        SearchRequest request = new SearchRequest();
        request.indices(indices).types(type).source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return Arrays.stream(response.getHits().getHits()).map(e -> JSON.parseObject(e.getSourceAsString(), clazz)).collect(Collectors.toList());
    }

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
     * id查询
     */
    public static <T> T findById(String indices, String type, String id, Class<T> clazz) throws IOException {
        GetRequest request = new GetRequest(indices, type, id);
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        return JSON.parseObject(response.getSourceAsString(), clazz);
    }

    /**
     * 深分页首页
     */
    public static <T> Map<String, Object> firstScroll(String indices, String type, Class<T> clazz) throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery()).size(2).sort("age", SortOrder.DESC);

        SearchRequest request = new SearchRequest();
        request.indices(indices).types(type).source(builder).scroll(TimeValue.timeValueMinutes(2L));

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        String scrollId = response.getScrollId();

        Predicate<SearchHit[]> predicate = i -> i != null && i.length > 0;
        List<T> list = null;
        if (predicate.test(hits)) {
            list = Arrays.stream(hits).map(e -> JSON.parseObject(e.getSourceAsString(), clazz)).collect(Collectors.toList());
        }

        Map<String, Object> map = new HashMap<>(2);
        map.put("scrollId", scrollId);
        map.put("data", list);
        return map;
    }

    /**
     * 深分页下一页
     */
    public static <T> List<T> nextScroll(String scrollId, Class<T> clazz) throws IOException {
        SearchScrollRequest request = new SearchScrollRequest(scrollId);
        request.scroll(TimeValue.timeValueMinutes(1L));
        SearchResponse response = client.scroll(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        Predicate<SearchHit[]> predicate = i -> i != null && i.length > 0;
        if (predicate.test(hits)) {
            return Arrays.stream(hits).map(e -> JSON.parseObject(e.getSourceAsString(), clazz)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * 清除深分页 scrollId
     */
    public static boolean clearScroll(String scrollId) throws IOException {
        ClearScrollRequest request = new ClearScrollRequest();
        request.addScrollId(scrollId);
        ClearScrollResponse response = client.clearScroll(request, RequestOptions.DEFAULT);
        return response.isSucceeded();
    }

    /**
     * 删除查询结果
     */
    public static boolean deleteByQuery(String indices, String type, QueryBuilder query) throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest();
        request.indices(indices);
        request.types(type).setQuery(query);
        BulkByScrollResponse response = client.deleteByQuery(request, RequestOptions.DEFAULT);

        return response.getBulkFailures().size() == 0;
    }

    /**
     *
     */
    public void boolQuery() throws IOException {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.should(QueryBuilders.termQuery("name", "zhangsan"));
        boolQuery.should(QueryBuilders.termQuery("name", "lisi"));
        boolQuery.should(QueryBuilders.termQuery("name", "wangwu"));

    }

    public static QueryBuilder multiShould(Object text, String... fieldNames) {
        return QueryBuilders.boolQuery().should(QueryBuilders.multiMatchQuery(text, fieldNames));
    }


}
