package com.example.jdk.util;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsTest {
    @Test
    public void toList() {
        Stream<Integer> s = Stream.of(1, 4, 6, 8);
        Collector<Integer, ?, List<Integer>> c = Collectors.toList();
        List<Integer> a = s.collect(c);
    }

    @Test
    public void toMap() {
        Stream<Integer> s = Stream.of(1, 4, 6, 8);
        Collector<Integer, ?, Map<Integer, Integer>> c = Collectors.toMap(i -> i, i -> i * 2);
        Map<Integer, Integer> a = s.collect(c);
    }

    @Test
    public void groupingBy() {
        Stream<Integer> s = Stream.of(1, 4, 6, 8);
        Collector<Integer, ?, Map<String, List<Integer>>> c = Collectors.groupingBy(i -> i > 5 ? "大于5" : "小于5");
        Map<String, List<Integer>> r = s.collect(c);
    }

    @Test
    public void joining() {
        Stream<String> s = Stream.of("a", "b", "c");
        Collector<CharSequence, ?, String> j = Collectors.joining();
        String c = s.collect(j);
    }

    @Test
    public void joining2() {
        Stream<String> s = Stream.of("a", "b", "c");
        Collector<CharSequence, ?, String> j = Collectors.joining(",");
        String c = s.collect(j);
    }

    @Test
    public void joining3() {
        Stream<String> s = Stream.of("a", "b", "c");
        Collector<CharSequence, ?, String> j = Collectors.joining(",", "{", "}");
        String c = s.collect(j);
    }


}
