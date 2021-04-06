package com.jamie.test;

import com.alibaba.fastjson.JSONObject;
import com.jamie.entity.User;
import com.jamie.utils.ESClient;
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
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.BoostingQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class ESTestMain {
    private RestHighLevelClient client = ESClient.getClient();
    private static final String INDEX = "lib";
    private static final String TYPE = "user";

    /**
     * 准备测试数据
     * GET /lib/user/_search
     *
     * indices().exists     索引操作，是否存在
     * indices().delete     索引删除
     * bulk                 批量操作
     *
     */
    @Test
    public void readyData4Test() throws IOException {
        //索引是否存在
        GetIndexRequest getIndexRequest = new GetIndexRequest().indices("lib");
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

        if (exists) {
            //删除索引
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest().indices("lib");
            client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        }

        //创建索引和批量创建文档
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest(INDEX, TYPE, "1").source(JSONObject.toJSONString(new User("zhaoliu", "hei long jiang sheng tie ling shi", 50, "1970-12-12", "喝酒游泳")), XContentType.JSON));
        bulkRequest.add(new IndexRequest(INDEX, TYPE, "2").source(JSONObject.toJSONString(new User("zhaoming", "bei jing hai dian qu qing he zhen", 20, "1998-10-12", "喝水跑步")), XContentType.JSON));
        bulkRequest.add(new IndexRequest(INDEX, TYPE, "3").source(JSONObject.toJSONString(new User("lisi", "bei jing hai dian qu qing he zhen", 23, "1970-12-12", "散步跳舞")), XContentType.JSON));
        bulkRequest.add(new IndexRequest(INDEX, TYPE, "4").source(JSONObject.toJSONString(new User("wangwu", "步行街", 26, "1998-10-12", "喝奶茶睡觉")), XContentType.JSON));
        bulkRequest.add(new IndexRequest(INDEX, TYPE, "5").source(JSONObject.toJSONString(new User("zhangsan", "bei jing chao yang qu", 29, "1988-10-12", "唱歌吃鸡")), XContentType.JSON));
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    /**
     * 根据id 批量删除文档
     */
    @Test
    public void bulkDeleteDoc() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest(INDEX, TYPE, "1"));
        request.add(new DeleteRequest(INDEX, TYPE, "2"));
        request.add(new DeleteRequest(INDEX, TYPE, "3"));
        client.bulk(request, RequestOptions.DEFAULT);
    }

    /**
     * 创建索引和文档
     */
    @Test
    public void createDoc() throws IOException {
        IndexRequest request = new IndexRequest(INDEX, TYPE, "20").source(JSONObject.toJSONString(new User("tim", "take that", 50, "1970-12-12", "cd")), XContentType.JSON);
        IndexResponse reponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println(reponse.getResult().toString());
    }

    /**
     * 更新文档
     */
    @Test
    public void updateDoc() throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "Jamie Zhou");

        UpdateRequest request = new UpdateRequest(INDEX, TYPE, "1").doc(map);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult().toString());
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDoc() throws IOException {
        DeleteRequest request = new DeleteRequest(INDEX, TYPE, "20");
        DeleteResponse respond = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(respond.getResult().toString());
    }

    private void printResult(SearchSourceBuilder builder) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(INDEX);
        request.types(TYPE);
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    /**
     * termsQuery
     * birthday 字段匹配 1970-12-12 或 1970-10-12
     */
    @Test
    public void termsQuery() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.termsQuery("birthday", "1970-12-12", "1998-10-12"));
        printResult(builder);
    }

    /**
     * multiMatchQuery
     * "interests"  或 "address" 字段 匹配"步"
     */
    @Test
    public void multiMatchQuery() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.multiMatchQuery("步", "interests", "address"));
        printResult(builder);
    }

    /**
     * matchQuery 带操作符
     * interests 字段 包含喝和跑字
     */
    @Test
    public void matchQueryWithOpt() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("interests", "喝 跑").operator(Operator.AND));
        printResult(builder);
    }

    /**
     * matchQuery 分词查询
     *
     * 分词分析
     * GET /lib/_analyze
     * {
     *   "field": "interests",
     *   "text": "喝酒睡觉"
     * }
     *
     * "喝酒睡觉" 分成 "喝"、"酒"、"睡"、"觉"
     */
    @Test
    public void matchQuery() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("interests", "喝酒睡觉"));
        printResult(builder);
    }

    /**
     * matchAllQuery
     *
     * 匹配全部
     */
    @Test
    public void matchAllQuery() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        printResult(builder);
    }

    /**
     * regexpQuery 匹配表达式
     */
    @Test
    public void findByRegexp() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.regexpQuery("name", "zha[a-z]*"));
        printResult(builder);
    }

    /**
     * 范围查询
     */
    @Test
    public void findByRange() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.rangeQuery("age").lte(28).gte(22));
        printResult(builder);

    }

    /**
     * 模糊查询
     */
    @Test
    public void findByFuzzy() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.fuzzyQuery("name", "zhangxan").prefixLength(5));
        printResult(builder);
    }

    /**
     * 前缀查询
     */
    @Test
    public void findByPrefix() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.prefixQuery("interests", "喝"));
        printResult(builder);
    }

    /**
     * 根据id查询
     */
    @Test
    public void findByIds() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.idsQuery().addIds("1", "2"));
        printResult(builder);
    }

    @Test
    public void findById() throws IOException {
        GetRequest request = new GetRequest("lib", "user", "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsMap());
    }

    /**
     * 深分页查询
     */
    @Test
    public void scrollQuery() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        builder.size(2);
        builder.sort("age", SortOrder.DESC);

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.scroll(TimeValue.timeValueMinutes(2L));
        request.source(builder);

        //search
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        String scrollId = response.getScrollId();
        System.out.println("===首页===");
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }

        while (true) {
            SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollId);
            searchScrollRequest.scroll(TimeValue.timeValueMinutes(1L));

            //scroll
            SearchResponse searchScrollResponse = client.scroll(searchScrollRequest, RequestOptions.DEFAULT);

            SearchHit[] hits = searchScrollResponse.getHits().getHits();
            if (hits != null && hits.length > 0) {
                System.out.println("===下一页===");
                for (SearchHit hit : hits) {
                    System.out.println(hit.getSourceAsMap());
                }
            } else {
                System.out.println("===结束===");
                break;
            }
        }

        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);

        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);

        System.out.println("删除scroll: " + clearScrollResponse.isSucceeded());
    }


    /**
     * 删除查询出来的结果
     */
    @Test
    public void deleteByQuery() throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest();
        request.indices("lib");
        request.types("user");
        request.setQuery(QueryBuilders.rangeQuery("age").lt(24));

        BulkByScrollResponse response = client.deleteByQuery(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());
    }

    /**
     * 指定分数，查询符合分数规则的文档
     */
    @Test
    public void boostingQuery() throws IOException {
        BoostingQueryBuilder boostingQuery = QueryBuilders.boostingQuery(
                QueryBuilders.matchQuery("interests", "喝"),
                QueryBuilders.matchQuery("name", "zhaoming")
        ).negativeBoost(0.5f);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(boostingQuery);

        printResult(builder);
    }


    /**
     * bool  查询
     * should 或关系
     * mustNot 不匹配
     * must 必须匹配
     */
    @Test
    public void boolQuery() throws IOException {

        /*
        # 名字 zhangsan或 lisi 或wangwu
        # 年龄不是26
        # 爱好包含唱，吃
         */
        BoolQueryBuilder boolQuery =QueryBuilders.boolQuery();
        boolQuery.should(QueryBuilders.termQuery("name", "zhangsan"));
        boolQuery.should(QueryBuilders.termQuery("name", "lisi"));
        boolQuery.should(QueryBuilders.termQuery("name", "wangwu"));
        boolQuery.mustNot(QueryBuilders.termQuery("age", 26));
        boolQuery.must(QueryBuilders.matchQuery("interests", "唱"));
        boolQuery.must(QueryBuilders.matchQuery("interests", "吃"));


        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(boolQuery);

        printResult(builder);
    }

    @Test
    public void filter() throws IOException {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.filter(QueryBuilders.termQuery("name", "zhangsan"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("age").lte(30));

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(boolQueryBuilder);

        printResult(builder);
    }


    @Test
    public void aggExtends() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.extendedStats("myagg").field("age"));

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        ExtendedStats agg = response.getAggregations().get("myagg");
        System.out.println(agg.getMax());
    }

    @Test
    public void aggRange() throws IOException {
        //统计	x<21岁、x<=21 x<28岁、x>28岁
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.range("myagg").field("age").addUnboundedTo(21).addRange(21, 28).addUnboundedFrom(28));

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        Range agg = response.getAggregations().get("myagg");
        for (Range.Bucket bucket : agg.getBuckets()) {
            Object  from = bucket.getFrom();
            Object to = bucket.getTo();
            String key = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();
            System.out.println(String.format("key=%s, docCount=%s, source=%s to=%s", key, docCount, from, to));
        }
    }

    @Test
    public void highlight() throws IOException {
        HighlightBuilder hBuilder = new HighlightBuilder();
        hBuilder.field("interests", 2).preTags("<font color='red'>").postTags("</font>");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("interests", "喝"));
        builder.highlighter(hBuilder);

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getHighlightFields().get("interests"));

        }
    }

    @Test
    public void aggCardinality() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.cardinality("group_birthday").field("birthday"));

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Cardinality birthdayAgg = response.getAggregations().get("group_birthday");
        System.out.println(birthdayAgg.getValue());
    }

    /**
     * 获取 term 聚合结果
     */
    @Test
    public void aggTest(){
        try {
            BoolQueryBuilder query = QueryBuilders.boolQuery().should(QueryBuilders.matchPhraseQuery("firstCateName", "bala"));

            TermsAggregationBuilder aggBuilder = AggregationBuilders.terms("myAgg").field("cat").size(1000);

            SearchSourceBuilder builder = new SearchSourceBuilder();
            builder.query(query);
            builder.aggregation(aggBuilder);
            builder.size(0);

            SearchRequest request = new SearchRequest();
            request.indices("inspection");
            request.types("index");
            request.source(builder);
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            Terms agg = response.getAggregations().get("myAgg");

            if (agg != null) {
                for (Terms.Bucket entry : agg.getBuckets()) {
                    String key = entry.getKeyAsString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
