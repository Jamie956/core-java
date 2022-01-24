package com.cat.json;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JonSort {
    static class Person {
        String name;
        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    /**
     * json double 排序
     */
    @Test
    public void doubleSort() {
        JSONObject j1 = new JSONObject();
        j1.put("value", 0.01);
        JSONObject j2 = new JSONObject();
        j2.put("value", 7.11);
        JSONObject j3 = new JSONObject();
        j3.put("value", 3.01);
        List<JSONObject> list = Arrays.asList(j1, j2, j3);
        List<JSONObject> value = list.stream().sorted(Comparator.comparingDouble(e -> e.getDoubleValue("value"))).collect(Collectors.toList());
        value.forEach(System.out::print);
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
