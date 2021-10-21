package com.jamie;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZJM
 * @date 2021/9/7 16:12
 */
public class TestEsUtil {
    @Test
    public void indexExist() throws IOException {
        EsClient esClient = new EsClient("lib");
        boolean b = esClient.indexExist();
    }

    @Test
    public void deleteIndex() throws IOException {
        EsClient esClient = new EsClient("lib");
        boolean lib = esClient.deleteIndex();
    }

    @Test
    public void bulkCreate() throws IOException {
        EsClient esClient = new EsClient("lib", "user");

        List<Object> list = Arrays.asList(
                new User("zhaoliu", "hei long jiang sheng tie ling shi", 50, "1970-12-12", "喝酒游泳"),
                new User("zhaoming", "bei jing hai dian qu qing he zhen", 20, "1998-10-12", "喝水跑步"),
                new User("lisi", "bei jing hai dian qu qing he zhen", 23, "1970-12-12", "散步跳舞"),
                new User("wangwu", "步行街", 26, "1998-10-12", "喝奶茶睡觉"),
                new User("zhangsan", "bei jing chao yang qu", 29, "1988-10-12", "唱歌吃鸡")
        );
        esClient.bulkCreateDoc(list);
    }

    @Test
    public void bulkCreate2() throws IOException {
        EsClient esClient = new EsClient("lib", "user");
        Map<String, Object> map = new HashMap<>(2);
        map.put("1", new User("wangwu", "gdfgfdgfdg", 26, "1998-10-12", "111"));
        map.put("2", new User("zhangsan", "bei jing chao yang qu", 29, "1988-10-12", "hfghfg"));
        esClient.bulkCreateDoc(map);
    }

    @Test
    public void bulkDeleteDoc() throws IOException {
        EsClient esClient = new EsClient("lib", "user");
        esClient.bulkDeleteDoc(Arrays.asList("1", "2"));
    }

    @Test
    public void createDoc() throws IOException {
        EsClient esClient = new EsClient("lib", "user");
        boolean ret = esClient.createDoc(new User("tim", "take that", 50, "1970-12-12", "cd"));
    }

    @Test
    public void updateDoc() throws IOException {
        HashMap<String, String> map = new HashMap<>(1);
        map.put("name", "Jamie Zhou");
        EsClient esClient = new EsClient("lib", "user");
        boolean b = esClient.updateDoc("W4Vnv3sBK-COOorJ7Cxj", map);
    }

    @Test
    public void deleteDoc() throws IOException {
        EsClient esClient = new EsClient("lib", "user");
        boolean b = esClient.deleteDoc("W4Vnv3sBK-COOorJ7Cxj");
    }

    @Test
    public void terms() throws IOException {
        EsClient esClient = new EsClient("lib", "user");
        QueryBuilder query = QueryBuilders.termsQuery("birthday", "1970-12-12", "1998-10-12");
        List<User> search = esClient.query(query, User.class);
    }

    @Test
    public void multiMatch() throws IOException {
        QueryBuilder query = QueryBuilders.multiMatchQuery("步", "interests", "address");
        EsClient esClient = new EsClient("lib", "user");
        List<User> search = esClient.query(query, User.class);
    }

    @Test
    public void match() throws IOException {
        QueryBuilder query = QueryBuilders.matchQuery("interests", "喝酒睡觉");
        EsClient esClient = new EsClient("lib", "user");
        List<User> search = esClient.query(query, User.class);
    }

    @Test
    public void range() throws IOException {
        QueryBuilder query = QueryBuilders.rangeQuery("age").gte(22).lte(28);
        EsClient esClient = new EsClient("lib", "user");
        List<User> search = esClient.query(query, User.class);
    }

    @Test
    public void findById() throws IOException {
        EsClient esClient = new EsClient("lib", "user");
        User search = esClient.findById("20", User.class);
    }

    @Test
    public void firstScroll() throws IOException {
        EsClient esClient = new EsClient("lib", "user");
        Map<String, Object> map = esClient.firstScroll(User.class);
        System.out.println(map);
    }

    @Test
    public void nextScroll() throws IOException {
        String scrollId = "DnF1ZXJ5VGhlbkZldGNoBQAAAAAAACyrFkJ0dFJIc01jU3R5clduV3NTODZBVlEAAAAAAAAsqRZCdHRSSHNNY1N0eXJXbldzUzg2QVZRAAAAAAAALKwWQnR0UkhzTWNTdHlyV25Xc1M4NkFWUQAAAAAAACyqFkJ0dFJIc01jU3R5clduV3NTODZBVlEAAAAAAAAsrRZCdHRSSHNNY1N0eXJXbldzUzg2QVZR";
        EsClient esClient = new EsClient("lib", "user");
        List<User> users = esClient.nextScroll(scrollId, User.class);
        System.out.println(users);
    }

    @Test
    public void clearScroll() throws IOException {
        EsClient esClient = new EsClient("lib", "user");
        boolean v = esClient.clearScroll("DnF1ZXJ5VGhlbkZldGNoBQAAAAAAACpeFkJ0dFJIc01jU3R5clduV3NTODZBVlEAAAAAAAAqXRZCdHRSSHNNY1N0eXJXbldzUzg2QVZRAAAAAAAAKl8WQnR0UkhzTWNTdHlyV25Xc1M4NkFWUQAAAAAAACpgFkJ0dFJIc01jU3R5clduV3NTODZBVlEAAAAAAAAqXBZCdHRSSHNNY1N0eXJXbldzUzg2QVZR");
    }

    @Test
    public void deleteByQuery() throws IOException {
        RangeQueryBuilder query = QueryBuilders.rangeQuery("age").lt(50);
        EsClient esClient = new EsClient("lib", "user");
        boolean fv = esClient.deleteByQuery(query);
    }

    @Test
    public void aggExtendsTest() throws IOException {
        AggregationBuilder aggBuilder = AggregationBuilders.extendedStats("myagg").field("age");
        EsClient esClient = new EsClient("lib", "user");
        Aggregation agg = esClient.agg(aggBuilder, "myagg");
        double max = ((ExtendedStats) agg).getMax();
    }

    @Test
    public void aggRange() throws IOException {
        AggregationBuilder aggBuilder = AggregationBuilders.range("group").field("age").addRange(21, 28);
        EsClient esClient = new EsClient("lib", "user");
        Range agg = (Range) esClient.agg(aggBuilder, "group");
        agg.getBuckets().forEach(e -> {
            String k = e.getKeyAsString();
            long v = e.getDocCount();
        });
    }

}
