package com.jamie.java8;


import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class FunctionalInterfaceTest {

    @Test
    public void testPredicate() {
        List<String> list = Arrays.asList("hello", "java8", "function", "predicate");
        Predicate<String> predicate = s -> s.length() > 5;

        //集合按 predicate 条件过滤
        List<String> collect = list.stream().filter(predicate).collect(Collectors.toList());

        collect.forEach(System.out::println);
    }

    @Test
    public void testFunction() {
        Function<String, String> function = String::toUpperCase;
        String ret = function.apply("abcdefg");
    }

    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }
        return list;
    }

    @Test
    public void testSupplier() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));

        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    public void modifyValue(Integer value, Consumer<Integer> consumer) {
        consumer.accept(value);
    }

    @Test
    public void testConsumer() {
        modifyValue(3, s -> System.out.println(s * 3));
    }

}
