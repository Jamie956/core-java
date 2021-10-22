package com.jamie.java8;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    @Data
    static class Person {
        String name;
        public Person(String name) {
            this.name = name;
        }
    }

    /**
     * stream: filter, distinct, limit, sorted, skip, iterate, map, reduce
     */
    @Test
    public void testStream() {
        //过滤返回false的元素
        List<Integer> a = Stream.of(1, 5, 6, 7).filter(i -> i > 5).collect(Collectors.toList());
        //去重
        List<Integer> b = Stream.of(1, 1, 6, 7).distinct().collect(Collectors.toList());
        //偏移
        List<Integer> c = Stream.of(1, 1, 6, 7).limit(2).collect(Collectors.toList());
        //排序
        List<Integer> e = Stream.of(6, 1, 7, 9, 3).sorted().collect(Collectors.toList());
        //跳过前n个元素
        List<Integer> f = Stream.of(6, 1, 7, 9, 3).skip(2).collect(Collectors.toList());
        //迭代生成元素
        List<Integer> g = Stream.iterate(0, n -> n + 2).limit(10).collect(Collectors.toList());
        //执行supplier 生成元素
        List<Double> h = Stream.generate(Math::random).limit(10).collect(Collectors.toList());
        //一进一出
        List<Integer> d = Stream.of(1, 1, 6, 7).map(i -> i + 1).collect(Collectors.toList());
        //多进一出
        BinaryOperator<Integer> func2 = (x1, x2) -> x1 + x2;
        Integer i = Stream.of(6, 1, 7, 9, 3).reduce(Integer::sum).orElse(0);
        Integer j = Stream.of(6, 1, 7, 9, 3).reduce(100, Integer::sum);


        List<Integer> list = Arrays.asList(1, 1, 6, 7);
        //获取第一个元素
        int e1 = list.stream().findFirst().get();
        int f1 = list.stream().findAny().get();
        long count = list.stream().count();
        int g1 = list.stream().max((x, y) -> y - x).get();
        int h1 = list.stream().min((x, y) -> y - x).get();


        Predicate<Integer> tester = s -> s > 5;
        boolean a1 = list.stream().allMatch(tester);
        boolean b1 = list.stream().anyMatch(tester);
        boolean c1 = list.stream().noneMatch(tester);
    }

    /**
     * map排序
     */
    @Test
    public void mapOrder() {
        Map<String, Integer> wordCounts = new HashMap<>();
        wordCounts.put("USA", 100);
        wordCounts.put("jobs", 200);
        wordCounts.put("software", 50);
        wordCounts.put("technology", 70);
        wordCounts.put("opportunity", 200);

        //升序
        Map<String, Integer> a = wordCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        //降序
        Map<String, Integer> b = wordCounts.entrySet().stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        //升序
        Map<String, Integer> c = wordCounts.entrySet().stream()
                .sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        //降序
        Map<String, Integer> d = wordCounts.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Test
    public void mapm() {
        //从对象列表中提取某一列
        Person p1 = new Person("Lord of the rings");
        Person p2 = new Person("Back to the future");
        Person p3 = new Person("Pulp fiction");
        Function<Person, String> getName = Person::getName;
        List<String> pss = Stream.of(p1, p2, p3).map(getName).collect(Collectors.toList());

        Function<Integer, JSONObject> mapper = e -> {
            JSONObject json = new JSONObject();
            json.put("k1", e);
            json.put("k2", e+"-");
            return json;
        };
        List<JSONObject> collect = Stream.of(6, 1, 7, 9, 3).map(mapper).collect(Collectors.toList());
    }

}
