package com.jamie.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorTest {
    @Data
    @AllArgsConstructor
    private static class Node  {
        private String theme ;
        private String type ;
        private String name ;
    }

    @Test
    public void testCollect() {
        //list stream 输出转 集合
        List<Integer> listCollector = Stream.of(1, 4, 6, 8).collect(Collectors.toList());
        //list stream 转map
        Map<Integer, Integer> mapCollector = Stream.of(1, 4, 7, 9).collect(Collectors.toMap(i -> i, i -> i * 2));
        //list stream 元素分组
        Map<String, List<Integer>> groupCollector = Stream.of(1, 4, 7, 9).collect(Collectors.groupingBy(i -> i > 5 ? "大于5" : "小于5"));
        //list stream 元素join 连接成字符串
        String c = Stream.of("a", "b", "c").collect(Collectors.joining());
        String d = Stream.of("a", "b", "c").collect(Collectors.joining(","));
        String e = Stream.of("a", "b", "c").collect(Collectors.joining(",", "{", "}"));
    }

    /**
     * 多条件分组
     */
    @Test
    public void multiGroupBy() {
        List<Node> list = new ArrayList<>();
        list.add(new Node("inspection", "industry", "产品"));
        list.add(new Node("inspection", "industry", "化妆品"));
        list.add(new Node("opinion", "injury", "产品致伤"));
        list.add(new Node("opinion", "injury", "食品致伤"));
        list.add(new Node("opinion", "damage", "致伤病"));
        list.add(new Node("opinion", "damage", "致财产损失"));

        Map<String, Map<String, List<String>>> collect = list.stream().collect(
                //按theme 分组
                Collectors.groupingBy(Node::getTheme,
                        //theme每个分组再次分组，按type分组
                        Collectors.groupingBy(Node::getType,
                                //分组元素收集为name
                                Collectors.mapping(Node::getName,
                                        Collectors.toList()))));
    }

}
