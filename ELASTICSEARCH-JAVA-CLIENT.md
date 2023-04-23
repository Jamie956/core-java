

elasticsearch version 6.8.8

kibana version 6.8.8



maven 依赖

```xml
<dependencies>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.10.2</version>
    </dependency>

    <dependency>
        <groupId>org.elasticsearch</groupId>
        <artifactId>elasticsearch</artifactId>
        <version>6.5.4</version>
    </dependency>
    <dependency>
        <groupId>org.elasticsearch.client</groupId>
        <artifactId>elasticsearch-rest-high-level-client</artifactId>
        <version>6.5.4</version>
    </dependency>

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.70</version>
    </dependency>

    <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>1.2</version>
    </dependency>

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.12</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```



elastic java client


```java
/**
 * kibana web
 * http://localhost:5601/app/kibana
 */
public class ElasticApiTest {
    public static RestHighLevelClient client;

    @Before
    public void init() {
        HttpHost http = new HttpHost("localhost", 9200);
        RestClientBuilder builder = RestClient.builder(http);
        client =  new RestHighLevelClient(builder);
    }

    // test whether or not exist index
    @Test
    public void indexExistTest() throws IOException {
        String index = "lib";
        GetIndexRequest getIndexRequest = new GetIndexRequest().indices(index);
        boolean result = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        Assert.assertFalse(result);
    }

    // delete index
    @Test
    public void deleteIndexTest() throws IOException {
        String index = "lib";
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest().indices(index);
        AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        boolean result = delete.isAcknowledged();
        Assert.assertTrue(result);
    }

    // 批量创建文档
    @Test
    public void bulkCreate() throws IOException {
        String userString1 = JSONObject.toJSONString(new User("zhaoliu", "hei long jiang sheng tie ling shi", 50, "1970-12-12", "喝酒游泳"));
        String userString2 = JSONObject.toJSONString(new User("zhaoming", "bei jing hai dian qu qing he zhen", 20, "1998-10-12", "喝水跑步"));
        String userString3 = JSONObject.toJSONString(new User("lisi", "bei jing hai dian qu qing he zhen", 23, "1970-12-12", "散步跳舞"));
        String userString4 = JSONObject.toJSONString(new User("wangwu", "步行街", 26, "1998-10-12", "喝奶茶睡觉"));
        String userString5 = JSONObject.toJSONString(new User("zhangsan", "bei jing chao yang qu", 29, "1988-10-12", "唱歌吃鸡"));

        String indices = "lib";
        String type = "user";

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest(indices, type).source(userString1, XContentType.JSON));
        bulkRequest.add(new IndexRequest(indices, type).source(userString2, XContentType.JSON));
        bulkRequest.add(new IndexRequest(indices, type).source(userString3, XContentType.JSON));
        bulkRequest.add(new IndexRequest(indices, type).source(userString4, XContentType.JSON));
        bulkRequest.add(new IndexRequest(indices, type).source(userString5, XContentType.JSON));
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);

        for (BulkItemResponse item : response.getItems()) {
            Assert.assertNull(item.getFailure());
        }
    }

    // 批量创建文档
    @Test
    public void bulkCreateWithId() throws IOException {
        String userString1 = JSONObject.toJSONString(new User("wangwu", "gdfgfdgfdg", 26, "1998-10-12", "111"));
        String userString2 = JSONObject.toJSONString(new User("zhangsan", "bei jing chao yang qu", 29, "1988-10-12", "hfghfg"));

        String indices = "lib";
        String type = "user";

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest(indices, type, "1").source(userString1, XContentType.JSON));
        bulkRequest.add(new IndexRequest(indices, type, "2").source(userString2, XContentType.JSON));
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);

        for (BulkItemResponse item : response.getItems()) {
            Assert.assertNull(item.getFailure());
        }
    }

    @Test
    public void bulkDeleteDoc() throws IOException {
        String indices = "lib";
        String type = "user";
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest(indices, type, "1"));
        request.add(new DeleteRequest(indices, type, "2"));
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        for (BulkItemResponse item : response.getItems()) {
            Assert.assertNull(item.getFailure());
        }
    }

    @Test
    public void createDoc() throws IOException {
        String indices = "lib";
        String type = "user";
        String userString = JSONObject.toJSONString(new User("tim", "take that", 50, "1970-12-12", "cd"));
        IndexRequest request = new IndexRequest(indices, type).source(userString, XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        Assert.assertEquals("created", response.getResult().getLowercase());
    }

    @Test
    public void createDocWithId() throws IOException {
        String indices = "lib";
        String type = "user";
        String userString = JSONObject.toJSONString(new User("tim", "take that", 50, "1970-12-12", "cd"));
        IndexRequest request = new IndexRequest(indices, type, "1").source(userString, XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        Assert.assertEquals("created", response.getResult().getLowercase());
    }

    @Test
    public void updateDocById() throws IOException {
        HashMap<String, String> map = new HashMap<>(1);
        map.put("name", "Jamie Zhou");

        String indices = "lib";
        String type = "user";
        String id = "1";

        UpdateRequest request = new UpdateRequest(indices, type, id).doc(map);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        Assert.assertEquals("updated", response.getResult().getLowercase());
    }

    @Test
    public void deleteDocById() throws IOException {
        String indices = "lib";
        String type = "user";
        String id = "1";

        DeleteRequest request = new DeleteRequest(indices, type, id);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        Assert.assertEquals("deleted", response.getResult().getLowercase());
    }

    @Test
    public void terms() throws IOException {
        String indices = "lib";
        String type = "user";
        QueryBuilder query = QueryBuilders.termsQuery("birthday", "1970-12-12", "1998-10-12");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(query);
        SearchRequest request = new SearchRequest();
        request.indices(indices).types(type).source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
    }

    @Test
    public void multiMatch() throws IOException {
        String indices = "lib";
        String type = "user";
        QueryBuilder query = QueryBuilders.multiMatchQuery("步", "interests", "address");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(query);
        SearchRequest request = new SearchRequest();
        request.indices(indices).types(type).source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
    }

    @Test
    public void match() throws IOException {
        String indices = "lib";
        String type = "user";
        QueryBuilder query = QueryBuilders.matchQuery("interests", "喝酒睡觉");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(query);
        SearchRequest request = new SearchRequest();
        request.indices(indices).types(type).source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
    }

    @Test
    public void range() throws IOException {
        String indices = "lib";
        String type = "user";
        QueryBuilder query = QueryBuilders.rangeQuery("age").gte(22).lte(28);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(query);
        SearchRequest request = new SearchRequest();
        request.indices(indices).types(type).source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
    }

    @Test
    public void findById() throws IOException {
        String indices = "lib";
        String type = "user";
        String id = "1";

        GetRequest request = new GetRequest(indices, type, id);
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
    }

    // 深分页首页
    @Test
    public void firstScroll() throws IOException {
        String indices = "lib";
        String type = "user";

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery()).size(2).sort("age", SortOrder.DESC);

        SearchRequest request = new SearchRequest();
        request.indices(indices).types(type).source(builder).scroll(TimeValue.timeValueMinutes(2L));

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        String scrollId = response.getScrollId();

        System.out.println(scrollId);
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
    }

    // 深分页下一页
    @Test
    public void nextScroll() throws IOException {
        String scrollId = "DnF1ZXJ5VGhlbkZldGNoBQAAAAAAAA5vFjZJQVVXakVmUWptNWI4a2FUNDdLZlEAAAAAAAAOchY2SUFVV2pFZlFqbTViOGthVDQ3S2ZRAAAAAAAADnEWNklBVVdqRWZRam01YjhrYVQ0N0tmUQAAAAAAAA5uFjZJQVVXakVmUWptNWI4a2FUNDdLZlEAAAAAAAAOcBY2SUFVV2pFZlFqbTViOGthVDQ3S2ZR";
        SearchScrollRequest request = new SearchScrollRequest(scrollId);
        request.scroll(TimeValue.timeValueMinutes(1L));
        SearchResponse response = client.scroll(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
    }

    // 清除深分页 scrollId
    @Test
    public void clearScroll() throws IOException {
        String scrollId = "DnF1ZXJ5VGhlbkZldGNoBQAAAAAAAA5vFjZJQVVXakVmUWptNWI4a2FUNDdLZlEAAAAAAAAOchY2SUFVV2pFZlFqbTViOGthVDQ3S2ZRAAAAAAAADnEWNklBVVdqRWZRam01YjhrYVQ0N0tmUQAAAAAAAA5uFjZJQVVXakVmUWptNWI4a2FUNDdLZlEAAAAAAAAOcBY2SUFVV2pFZlFqbTViOGthVDQ3S2ZR";

        ClearScrollRequest request = new ClearScrollRequest();
        request.addScrollId(scrollId);
        ClearScrollResponse response = client.clearScroll(request, RequestOptions.DEFAULT);
        System.out.println(response.isSucceeded());
    }

    @Test
    public void deleteByQuery() throws IOException {
        String indices = "lib";
        String type = "user";
        RangeQueryBuilder query = QueryBuilders.rangeQuery("age").lt(50);

        DeleteByQueryRequest request = new DeleteByQueryRequest();
        request.indices(indices);
        request.types(type).setQuery(query);
        client.deleteByQuery(request, RequestOptions.DEFAULT);
    }

    // agg max value
    @Test
    public void aggExtendsTest() throws IOException {
        String indices = "lib";
        String type = "user";
        String name = "myagg";

        AggregationBuilder aggBuilder = AggregationBuilders.extendedStats(name).field("age");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.aggregation(aggBuilder).size(0);
        SearchRequest request = new SearchRequest();
        request.indices(indices).types(type).source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Aggregation myagg = response.getAggregations().get(name);

        double max = ((ExtendedStats) myagg).getMax();
        System.out.println(max);
    }

    @Test
    public void aggRange() throws IOException {
        String indices = "lib";
        String type = "user";
        String name = "group";

        AggregationBuilder aggBuilder = AggregationBuilders.range(name).field("age").addRange(21, 28);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.aggregation(aggBuilder).size(0);
        SearchRequest request = new SearchRequest();
        request.indices(indices).types(type).source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Range agg = response.getAggregations().get(name);

        agg.getBuckets().forEach(e -> {
            String k = e.getKeyAsString();
            long v = e.getDocCount();
            System.out.println(k);
            System.out.println(v);
        });
    }

    // 搜索词空格分词查询，全匹配
    @Test
    public void keywordSearchTest() {
        QueryBuilders.matchQuery("title", "安徽 生姜").operator(Operator.AND).analyzer("whitespace");
    }

    // 获取索引文档的全部字段
    @Test
    public void getEsMappingProperty() throws IOException {
        String index = "test_index";
        String type = "index";

        GetIndexRequest request = new GetIndexRequest().indices(index);
        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);

        JSONObject jsonResponse = JSON.parseObject(response.toString());
        Set<String> fields = jsonResponse.getJSONObject(index).getJSONObject("mappings").getJSONObject(type).getJSONObject("properties").keySet();

        System.out.println(fields);
    }

    // 批量更新，不存在就创建
    @Test
    public void batchUpdateInsert() throws IOException {
        String index = "my_index";
        String type = "_doc";

        JSONObject doc1 = new JSONObject();
        doc1.put("name", "tom");
        doc1.put("age", 11);
        UpdateRequest updateRequest1 = new UpdateRequest(index, type, "10001").doc(doc1).docAsUpsert(true);

        JSONObject doc2 = new JSONObject();
        doc2.put("name", "tim");
        doc2.put("age", 22);
        UpdateRequest updateRequest2 = new UpdateRequest(index, type, "10002").doc(doc2).docAsUpsert(true);

        BulkRequest request = new BulkRequest();
        request.add(updateRequest1);
        request.add(updateRequest2);

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
    }
}
```



```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String address;
    private Integer age;
    private String birthday;
    private String interests;
    @JSONField(format = "yyyy-MM-dd")
    private Date left;

    public User(String name, String address, Integer age, String birthday, String interests) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.birthday = birthday;
        this.interests = interests;
    }
}
```