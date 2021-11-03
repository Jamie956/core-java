package com.jamie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
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
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author ZJM
 * @date 2021/9/13 9:42
 */
@Data
public class ElasticClient {
    private String indices;
    private String type;
    public static RestHighLevelClient client;

    static {
        HttpHost http = new HttpHost("localhost", 9200);
        RestClientBuilder builder = RestClient.builder(http);
        client =  new RestHighLevelClient(builder);
    }

    ElasticClient(String indices, String type) {
        this.indices = indices;
        this.type = type;
    }

    ElasticClient(String indices) {
        this.indices = indices;
    }

    /**
     * 判断索引是否存在
     * @return 是否存在
     */
    public boolean indexExist() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest().indices(indices);
        return client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 删除索引
     * @return 是否删除成功
     */
    public boolean deleteIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest().indices(indices);
        AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        return delete.isAcknowledged();
    }

    /**
     * 批量创建文档
     * @param list 文档对象
     */
    public void bulkCreateDoc(List<Object> list) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        list.forEach(e -> bulkRequest.add(new IndexRequest(indices, type).source(JSONObject.toJSONString(e), XContentType.JSON)));
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    /**
     * 批量创建文档
     */
    public void bulkCreateDocByJSONArray(JSONArray jsonArray) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        jsonArray.forEach(e -> bulkRequest.add(new IndexRequest(indices, type).source(JSONObject.toJSONString(e), XContentType.JSON)));
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    /**
     * 批量创建文档
     * @param map 文档对象
     */
    public void bulkCreateDoc(Map<String, Object> map) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        map.forEach((k,v) -> bulkRequest.add(new IndexRequest(indices, type, k).source(JSONObject.toJSONString(v), XContentType.JSON)));
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    /**
     * 批量删除文档
     * @param ids id集合
     */
    public void bulkDeleteDoc(List<String> ids) throws IOException {
        BulkRequest request = new BulkRequest();
        ids.forEach(e -> request.add(new DeleteRequest(indices, type, e)));
        client.bulk(request, RequestOptions.DEFAULT);
    }

    /**
     * 创建文档
     * @param o 文档对象
     */
    public boolean createDoc(Object o) throws IOException {
        IndexRequest request = new IndexRequest(indices, type).source(JSONObject.toJSONString(o), XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        return "created".equals(response.getResult().getLowercase());
    }

    /**
     * 更新文档
     */
    public boolean updateDoc(String id, Map<String, String> map) throws IOException {
        UpdateRequest request = new UpdateRequest(indices, type, id).doc(map);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        return "updated".equals(response.getResult().getLowercase());
    }

    /**
     * 删除文档
     */
    public boolean deleteDoc(String id) throws IOException {
        DeleteRequest request = new DeleteRequest(indices, type, id);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        return "deleted".equals(response.getResult().getLowercase());
    }

    public void query(SearchSourceBuilder builder) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(indices).types(type).source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    public <T> List<T> query(QueryBuilder query, Class<T> clazz) throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(query);

        SearchRequest request = new SearchRequest();
        request.indices(indices).types(type).source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return Arrays.stream(response.getHits().getHits()).map(e -> JSON.parseObject(e.getSourceAsString(), clazz)).collect(Collectors.toList());
    }

    public Aggregation agg(AggregationBuilder agg, String name) throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.aggregation(agg).size(0);
        SearchRequest request = new SearchRequest();
        request.indices(indices).types(type).source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return response.getAggregations().get(name);
    }

    /**
     * id查询
     */
    public <T> T findById(String id, Class<T> clazz) throws IOException {
        GetRequest request = new GetRequest(indices, type, id);
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        return JSON.parseObject(response.getSourceAsString(), clazz);
    }

    /**
     * 深分页首页
     */
    public <T> Map<String, Object> firstScroll(Class<T> clazz) throws IOException {
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
    public <T> List<T> nextScroll(String scrollId, Class<T> clazz) throws IOException {
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
    public boolean clearScroll(String scrollId) throws IOException {
        ClearScrollRequest request = new ClearScrollRequest();
        request.addScrollId(scrollId);
        ClearScrollResponse response = client.clearScroll(request, RequestOptions.DEFAULT);
        return response.isSucceeded();
    }

    /**
     * 删除查询结果
     */
    public boolean deleteByQuery(QueryBuilder query) throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest();
        request.indices(indices);
        request.types(type).setQuery(query);
        BulkByScrollResponse response = client.deleteByQuery(request, RequestOptions.DEFAULT);

        return response.getBulkFailures().size() == 0;
    }

    /**
     * update content where content_id=?
     */
    public void updateByQuery(String field, String value, String updateField, String updateValue) throws IOException {
        UpdateByQueryRequest request = new UpdateByQueryRequest(indices);
        request.setConflicts("proceed");
        request.setQuery(new TermQueryBuilder(field, value));
        // 更新最大文档数
        request.setSize(10);
        // 批次大小
        request.setBatchSize(1000);
        Map<String, Object> paramMaps = new HashMap<>(1);
        paramMaps.put(updateField, updateValue);
        request.setScript(new Script(ScriptType.INLINE, "painless", "ctx._source.content = params.content;", paramMaps));
        // 并行
        request.setSlices(2);
        // 使用滚动参数来控制“搜索上下文”存活的时间
        request.setScroll(TimeValue.timeValueMinutes(10));
        // 超时
        request.setTimeout(TimeValue.timeValueMinutes(2));
        // 刷新索引
        request.setRefresh(true);
        client.updateByQuery(request, RequestOptions.DEFAULT);
    }

}
