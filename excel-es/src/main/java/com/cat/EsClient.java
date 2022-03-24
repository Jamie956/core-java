package com.cat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EsClient {
    public static RestHighLevelClient client;

    static {
        HttpHost http = new HttpHost("localhost", 9200);
        RestClientBuilder builder = RestClient.builder(http);
        client = new RestHighLevelClient(builder);
    }

    /**
     * Elasticsearch 批量更新文档，更新冲突时重试3次，文档不存在时创建文档
     *
     * @param items Json 数据集
     */
    public void bulkUpdateInsert(String index, String type, List<JSONObject> items) throws IOException {
        BulkRequest request = new BulkRequest();
        items.forEach(e -> request.add(new UpdateRequest(index, type, e.getString("id")).doc(e).docAsUpsert(true).retryOnConflict(3)));
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        if (response.hasFailures()) {
            log.info("更新失败：" + response.buildFailureMessage());
        } else {
            log.info("更新成功，耗时：" + response.getTook());
        }
    }

    /**
     * ES ids 查询
     */
    public List<JSONObject> idsQuery(String index, String type, String[] ids, int size) throws IOException {
        if (indexExist(index)) {
            SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.idsQuery().addIds(ids)).size(size);
            SearchRequest request = new SearchRequest().indices(index).types(type).source(builder);
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            return Arrays.stream(response.getHits().getHits()).map(e -> JSON.parseObject(e.getSourceAsString())).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * 索引是否存在
     */
    public boolean indexExist(String index) throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest().indices(index);
        return client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    }
}
