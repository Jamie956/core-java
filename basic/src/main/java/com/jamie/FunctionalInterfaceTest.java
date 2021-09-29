package com.jamie;


import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class FunctionalInterfaceTest {

    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    @Test
    public void testPredicate() {
        filterStr(Arrays.asList("hello", "java8", "function", "predicate"), s -> s.length() > 5).forEach(System.out::println);
    }

    public String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    @Test
    public void testFunction() {
        String str1 = strHandler("测试内置函数式接口", s -> s.substring(2));
        System.out.println(str1);

        String str2 = strHandler("abcdefg", String::toUpperCase);
        System.out.println(str2);
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
