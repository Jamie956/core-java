package com.jamie;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
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
        boolean usr = EsUtil.indexExist("usr");
        boolean lib = EsUtil.indexExist("lib");
    }

    @Test
    public void deleteIndex() throws IOException {
        boolean lib = EsUtil.deleteIndex("lib");
    }

    @Test
    public void bulkCreate() throws IOException {
        List<Object> list = Arrays.asList(
                new User("zhaoliu", "hei long jiang sheng tie ling shi", 50, "1970-12-12", "喝酒游泳"),
                new User("zhaoming", "bei jing hai dian qu qing he zhen", 20, "1998-10-12", "喝水跑步"),
                new User("lisi", "bei jing hai dian qu qing he zhen", 23, "1970-12-12", "散步跳舞"),
                new User("wangwu", "步行街", 26, "1998-10-12", "喝奶茶睡觉"),
                new User("zhangsan", "bei jing chao yang qu", 29, "1988-10-12", "唱歌吃鸡")
        );
        EsUtil.bulkCreateDoc("lib", "user", list);
    }

    @Test
    public void bulkCreate2() throws IOException {
        Map<String, Object> map = new HashMap<>(2);
        map.put("1", new User("wangwu", "gdfgfdgfdg", 26, "1998-10-12", "111"));
        map.put("2", new User("zhangsan", "bei jing chao yang qu", 29, "1988-10-12", "hfghfg"));
        EsUtil.bulkCreateDoc("lib", "user", map);
    }

    @Test
    public void bulkDeleteDoc() throws IOException {
        EsUtil.bulkDeleteDoc("lib", "user", Arrays.asList("1", "2"));
    }

    @Test
    public void createDoc() throws IOException {
        boolean ret = EsUtil.createDoc("lib", "user", new User("tim", "take that", 50, "1970-12-12", "cd"));
    }

    @Test
    public void updateDoc() throws IOException {
        HashMap<String, String> map = new HashMap<>(1);
        map.put("name", "Jamie Zhou");
        boolean b = EsUtil.updateDoc("lib", "user", "W4Vnv3sBK-COOorJ7Cxj", map);
    }

    @Test
    public void deleteDoc() throws IOException {
        boolean b = EsUtil.deleteDoc("lib", "user", "W4Vnv3sBK-COOorJ7Cxj");
    }

    @Test
    public void terms() throws IOException {
        QueryBuilder query = EsUtil.terms("birthday", "1970-12-12", "1998-10-12");
        List<User> search = EsUtil.search("lib", "user", query, User.class);
    }

    @Test
    public void multiMatch() throws IOException {
        QueryBuilder query = EsUtil.multiMatch("步", "interests", "address");
        List<User> search = EsUtil.search("lib", "user", query, User.class);
    }

    @Test
    public void match() throws IOException {
        QueryBuilder query = EsUtil.match("interests", "喝酒睡觉");
        List<User> search = EsUtil.search("lib", "user", query, User.class);
    }

    @Test
    public void range() throws IOException {
        QueryBuilder query = EsUtil.range("age", 22, 28);
        List<User> search = EsUtil.search("lib", "user", query, User.class);
    }

    @Test
    public void findById() throws IOException {
        User search = EsUtil.findById("lib", "user", "20", User.class);
    }

    @Test
    public void firstScroll() throws IOException {
        Map<String, Object> map = EsUtil.firstScroll("lib", "user", User.class);
        System.out.println(map);
    }

    @Test
    public void nextScroll() throws IOException {
        String scrollId = "DnF1ZXJ5VGhlbkZldGNoBQAAAAAAACyrFkJ0dFJIc01jU3R5clduV3NTODZBVlEAAAAAAAAsqRZCdHRSSHNNY1N0eXJXbldzUzg2QVZRAAAAAAAALKwWQnR0UkhzTWNTdHlyV25Xc1M4NkFWUQAAAAAAACyqFkJ0dFJIc01jU3R5clduV3NTODZBVlEAAAAAAAAsrRZCdHRSSHNNY1N0eXJXbldzUzg2QVZR";
        List<User> users = EsUtil.nextScroll(scrollId, User.class);
        System.out.println(users);
    }

    @Test
    public void clearScroll() throws IOException {
        boolean v = EsUtil.clearScroll("DnF1ZXJ5VGhlbkZldGNoBQAAAAAAACpeFkJ0dFJIc01jU3R5clduV3NTODZBVlEAAAAAAAAqXRZCdHRSSHNNY1N0eXJXbldzUzg2QVZRAAAAAAAAKl8WQnR0UkhzTWNTdHlyV25Xc1M4NkFWUQAAAAAAACpgFkJ0dFJIc01jU3R5clduV3NTODZBVlEAAAAAAAAqXBZCdHRSSHNNY1N0eXJXbldzUzg2QVZR");
    }

    @Test
    public void deleteByQuery() throws IOException {
        RangeQueryBuilder query = QueryBuilders.rangeQuery("age").lt(50);
        boolean fv = EsUtil.deleteByQuery("lib", "user", query);
    }

}
