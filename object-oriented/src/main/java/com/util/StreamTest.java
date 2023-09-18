package com.util;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.*;

public class StreamTest {

    @Test
    public void filter() {
        Stream<Integer> s = Stream.of(1, 5, 6, 7);
        Stream<Integer> s1 = s.filter(i -> i > 5);
        Object[] arr = s1.toArray();
    }

    @Test
    public void map() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Stream<Integer> s1 = s.map(i -> i + 1);
        Object[] arr = s1.toArray();
    }

    @Test
    public void mapToInt() {
        Stream<String> s = Stream.of("1", "1", "6", "7");
        IntStream s1 = s.mapToInt(i -> Integer.valueOf(i));
        int[] arr = s1.toArray();
    }

    @Test
    public void mapToLong() {
        Stream<String> s = Stream.of("1", "1", "6", "7");
        LongStream s1 = s.mapToLong(i -> Long.valueOf(i));
        long[] arr = s1.toArray();
    }

    @Test
    public void mapToDouble() {
        Stream<String> s = Stream.of("1", "1", "6", "7");
        DoubleStream s1 = s.mapToDouble(i -> Double.valueOf(i));
        double[] arr = s1.toArray();
    }

    @Test
    public void flatMap() {
        Stream<String> s = Stream.of("6", "7");

        Function<String, Stream<Integer>> f = e -> {
            int i = Integer.parseInt(e);
            return Stream.of(i, i * 2);
        };

        Stream<Integer> s1 = s.flatMap(f);
        Object[] arr = s1.toArray();
    }

    @Test
    public void flatMapToInt() {
        Stream<String> s = Stream.of("6", "7");

        Function<String, IntStream> f = e -> {
            int i = Integer.parseInt(e);
            return IntStream.of(i, i * 2);
        };

        IntStream s1 = s.flatMapToInt(f);
        int[] arr = s1.toArray();
    }

    @Test
    public void distinct() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Stream<Integer> s1 = s.distinct();
        Object[] arr = s1.toArray();
    }

    @Test
    public void sorted() {
        Stream<Integer> s = Stream.of(5, 9, 6, 7, 1);
        Stream<Integer> s1 = s.sorted();
        Object[] arr = s1.toArray();
    }

    @Test
    public void sorted2() {
        Stream<Integer> s = Stream.of(5, 9, 6, 7, 1);
        Comparator<Object> c = Comparator.comparingInt(e -> (int) e);
        Stream<Integer> s1 = s.sorted(c);
        Object[] arr = s1.toArray();
    }

    @Test
    public void peek() {
        Stream<Integer> s = Stream.of(5, 9, 6, 7, 1);
        Consumer<Integer> c = e -> System.out.println(e);
        Stream<Integer> s1 = s.peek(c);
        Object[] arr = s1.toArray();
    }

    @Test
    public void limit() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Stream<Integer> s1 = s.limit(2);
        Object[] arr = s1.toArray();
    }

    @Test
    public void skip() {
        Stream<Integer> s = Stream.of(6, 1, 7, 9, 3);
        Stream<Integer> s1 = s.skip(2);
        Object[] arr = s1.toArray();
    }

    @Test
    public void forEach() {
        Stream<Integer> s = Stream.of(6, 1, 7, 9, 3);
        Consumer<Integer> c = e -> System.out.println(e);
        s.forEach(c);
    }

    @Test
    public void forEachOrdered() {
        Stream<Integer> s = Stream.of(6, 1, 7, 9, 3);
        Consumer<Integer> c = e -> System.out.println(e);
        s.forEachOrdered(c);
    }

    @Test
    public void toArray() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Object[] arr = s.toArray();
    }

    @Test
    public void toArray2() {
        Stream<String> s = Stream.of("6", "7");
        IntFunction<String[]> f = e -> new String[]{"1", "2"};
        String[] arr = s.toArray(f);
    }

    @Test
    public void reduce() {
        Stream<Integer> s = Stream.of(8, 2);
        BinaryOperator<Integer> f = (a, b) -> a + b;
        Integer r = s.reduce(100, f);
    }

    @Test
    public void reduce2() {
        Stream<Integer> s = Stream.of(8, 2);
        BinaryOperator<Integer> f = (a, b) -> a + b;
        Optional<Integer> r = s.reduce(f);
    }

    @Test
    public void reduce3() {
        Stream<Integer> s = Stream.of(8, 2);
        BiFunction<Integer, Integer, Integer> f0 = (a, b) -> a + b + 1;
        BinaryOperator<Integer> f = (a, b) -> a + b;
        Integer r = s.reduce(100, f0, f);
    }

    @Test
    public void collect() {
        Stream<Integer> s = Stream.of(8, 2, 5);
        Supplier<String> f1 = () -> "10";
        BiConsumer<String, Integer> f2 = (a, b) -> System.out.println(a + "-" + b);
        BiConsumer<String, String> f3 = (a, b) -> System.out.println(a + "-" + b);

        String c = s.collect(f1, f2, f3);
    }

    @Test
    public void collect2() {
        Stream<Integer> s = Stream.of(8, 2, 5);
        List<Integer> c = s.collect(Collectors.toList());
    }

    @Test
    public void min() {
        Stream<Integer> s = Stream.of(8, 2, 5);
        Comparator<Object> c = Comparator.comparingInt(e -> (int) e);
        Optional<Integer> o = s.min(c);
    }

    @Test
    public void max() {
        Stream<Integer> s = Stream.of(8, 2, 5);
        Comparator<Object> c = Comparator.comparingInt(e -> (int) e);
        Optional<Integer> o = s.max(c);
    }

    @Test
    public void count() {
        Stream<Integer> s = Stream.of(8, 2, 5);
        long c = s.count();
    }

    @Test
    public void anyMatch() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Predicate<Integer> f = e -> e > 5;
        boolean b = s.anyMatch(f);
    }

    @Test
    public void allMatch() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Predicate<Integer> f = e -> e > 5;
        boolean b = s.allMatch(f);
    }

    @Test
    public void noneMatch() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Predicate<Integer> f = e -> e > 5;
        boolean b = s.noneMatch(f);
    }

    @Test
    public void findFirst() {
        Stream<Integer> s = Stream.of(1, 1, 6, 7);
        Optional<Integer> o = s.findFirst();
    }

    @Test
    public void findAny() {
        Stream<Integer> s = Stream.of(6, 7);
        Optional<Integer> o = s.findAny();
    }

    @Test
    public void of() {
        Stream<Integer> s = Stream.of(6);
    }

    @Test
    public void of2() {
        Stream<Integer> s = Stream.of(6, 7);
    }

    @Test
    public void iterate() {
        Stream<Integer> s = Stream.iterate(1, n -> n * 2).limit(5);
        Object[] arr = s.toArray();
    }

    @Test
    public void generate() {
        Supplier<Double> f = () -> Math.random();
        Stream<Double> s = Stream.generate(f).limit(10);
        Object[] arr = s.toArray();
    }

    @Test
    public void concat() {
        Stream<Integer> s = Stream.of(6, 7);
        Stream<Integer> s2 = Stream.of(3, 0);
        Stream<Integer> s3 = Stream.concat(s, s2);
        Object[] arr = s3.toArray();
    }
}
