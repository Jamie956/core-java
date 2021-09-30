package com.jamie;

import com.jamie.entity.CgTagEntity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorTest {

    /**
     * 收集器 Collectors
     */
    @Test
    public void testCollect() {
        //list 收集
        List<Integer> a0 = Stream.of(1, 4, 6, 8).collect(Collectors.toList());
        //map 收集
        Map<String, String> a = Stream.of(1, 4, 7, 9).collect(Collectors.toMap(i -> i + "key", i -> i + "value"));
        //分组 收集
        Map<String, List<Integer>> b = Stream.of(1, 4, 7, 9).collect(Collectors.groupingBy(i -> i > 5 ? "> 5" : "< 5"));
        //join 收集
        String c = Stream.of("a", "b", "c").collect(Collectors.joining());
        String d = Stream.of("a", "b", "c").collect(Collectors.joining(","));
        String e = Stream.of("a", "b", "c").collect(Collectors.joining(",", "{", "}"));
    }

    /**
     * 多次分组
     */
    public void multiGroupBy(){
        List<CgTagEntity> list = new ArrayList<>();
        list.add(new CgTagEntity("inspection", "industry", "产品"));
        list.add(new CgTagEntity("inspection", "industry", "化妆品"));
        list.add(new CgTagEntity("opinion", "injury", "产品致伤"));
        list.add(new CgTagEntity("opinion", "injury", "食品致伤"));
        list.add(new CgTagEntity("opinion", "damage", "致伤病"));
        list.add(new CgTagEntity("opinion", "damage", "致财产损失"));

        Map<String, Map<String, List<String>>> collect = list.stream().collect(
                Collectors.groupingBy(CgTagEntity::getTheme,
                        Collectors.groupingBy(CgTagEntity::getType,
                                Collectors.mapping(CgTagEntity::getName, Collectors.toList())))
        );
    }

}
