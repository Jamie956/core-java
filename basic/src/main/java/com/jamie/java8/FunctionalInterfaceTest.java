package com.jamie.java8;


import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalInterfaceTest {

    @Test
    public void predicateFilterTest() {
        //@FunctionalInterface  <T> -> boolean
        Predicate<String> predicate = s -> s.length() > 5;
        //集合按 predicate 条件过滤
        List<String> collect = Stream.of("hello", "java8", "function", "predicate").filter(predicate).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Test
    public void testFunction() {
        //@FunctionalInterface  <T> -> <V>
        Function<String, String> function = String::toUpperCase;
        String ret = function.apply("abcdefg");
    }

    @Test
    public void testSupplier() {
        //@FunctionalInterface () -> <T>
        Supplier<Integer> supplier = () -> (int) (Math.random() * 100);
        Integer integer = supplier.get();
    }

    @Test
    public void testConsumer() {
        //@FunctionalInterface  <T> -> void
        Consumer<Integer> consumer = s -> System.out.println(s * 3);
        consumer.accept(3);
    }

    /**
     * 两个整数是否相等
     * @param a 数字
     * @param b 数字
     * @return 是否相等
     */
    public static boolean isEqual(int a, int b) {
        Predicate<Integer> predicate = i -> i == a;
        return predicate.test(b);
    }

    /**
     * 是否等于7
     * @param a 数字
     * @return 是否等于7
     */
    public static boolean isEqualSeven(int a) {
        Predicate<Object> predicate = Predicate.isEqual(7);
        return predicate.test(a);
    }

    /**
     * 是否在范围内
     * @param from 起始
     * @param to 结束
     * @param num 测试数
     * @return 是否在范围内
     */
    public static boolean inRange(int from, int to, int num) {
        Predicate<Integer> fromPredicate = i -> i >= from;
        Predicate<Integer> toPredicate = i -> i <= to;
        return fromPredicate.and(toPredicate).test(num);
    }

    /**
     * 是否是偶数
     * @param num 数字
     * @return 是否是偶数
     */
    public static boolean isEvenNumber(int num) {
        Predicate<Integer> predicate = i -> i % 2 == 0;
        return predicate.test(num);
    }

    /**
     * 是否是奇数
     * @param num 数字
     * @return 是否是奇数
     */
    public static boolean isOdd(int num) {
        Predicate<Integer> predicate = i -> i % 2 == 0;
        return predicate.negate().test(num);
    }

    /**
     * 找出全部奇数
     * @param list 数字集合
     * @return 奇数
     */
    public static List<Integer> findOdd(List<Integer> list) {
        Predicate<Integer> predicate = i -> i % 2 == 0;
        return list.stream().filter(predicate.negate()).collect(Collectors.toList());
    }

    /**
     * Predicate< T >	接收T对象，返回boolean
     */
    @Test
    public void testPredicate1() {
        boolean equal = isEqual(5, 5);
        boolean equal1 = isEqualSeven(7);
        boolean inr = inRange(5, 20, 21);
        boolean evenNumber = isEvenNumber(9);
        boolean odd = isOdd(9);
        List<Integer> odd1 = findOdd(Arrays.asList(1, 2, 3, 4, 5));
    }

    /**
     * Consumer< T >	接收T对象，无返回
     */
    @Test
    public void consumer() {
        String a = "asas";
        Consumer<String> c1 = message -> {
            System.out.print("1:" + message + a);
        };
        Consumer<String> c2 = message -> {
            System.out.println("2:" + message);
        };
        c1.andThen(c2).accept("x");
    }

    /**
     * BiConsumer< T, V > 接收T，V对象, 无返回
     */
    @Test
    public void biConsumer() {
        BiConsumer<Integer, Integer> biConsumer = (a, b) -> System.out.println(a + b);
        biConsumer.accept(1, 1);
    }

    /**
     * Function< T, R >	接收T对象，返回R
     */
    @Test
    public void function() {
        Function<Integer, String> mapper = i -> i + "...";
        String data = mapper.apply(111);
    }

    /**
     * Supplier< T > 不接收对象，返回T
     */
    @Test
    public void supplier() {
        Supplier<String> uuid = () -> UUID.randomUUID().toString();
        String s = uuid.get();
    }
}
