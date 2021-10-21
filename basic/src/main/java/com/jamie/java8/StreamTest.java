package com.jamie.java8;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    @Data
    @AllArgsConstructor
    static class Person2 implements Serializable {
        private static final long serialVersionUID = -2687432631518129972L;

        private String name;
        private Double length;

        public Person2() {
            this.name = "tom";
        }

        public Person2(String name) {
            this.name = name;
        }
    }

    private static class Person {
        public enum Sex {
            MALE, FEMALE
        }

        String name;
        LocalDate birthday;
        Sex gender;
        String emailAddress;

        Person(String nameArg, LocalDate birthdayArg, Sex genderArg, String emailArg) {
            name = nameArg;
            birthday = birthdayArg;
            gender = genderArg;
            emailAddress = emailArg;
        }

        public int getAge() {
            return birthday.until(IsoChronology.INSTANCE.dateNow()).getYears();
        }

        public void printPerson() {
            System.out.println(name + ", " + this.getAge());
        }

        public Sex getGender() {
            return gender;
        }

        public String getName() {
            return name;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public LocalDate getBirthday() {
            return birthday;
        }

        public static int compareByAge(Person a, Person b) {
            return a.birthday.compareTo(b.birthday);
        }

        public static List<Person> createRoster() {
            List<Person> roster = new ArrayList<>();
            roster.add(new Person("Fred", IsoChronology.INSTANCE.date(1980, 6, 20), Person.Sex.MALE, "fred@example.com"));
            roster.add(new Person("Jane", IsoChronology.INSTANCE.date(1990, 7, 15), Person.Sex.FEMALE, "jane@example.com"));
            roster.add(new Person("George", IsoChronology.INSTANCE.date(1991, 8, 13), Person.Sex.MALE, "george@example.com"));
            roster.add(new Person("Bob", IsoChronology.INSTANCE.date(2000, 9, 12), Person.Sex.MALE, "bob@example.com"));
            return roster;
        }
    }

    List<Person> persons = Person.createRoster();

    //内部迭代
    @Test
    public void test2() {
        Stream<Person> stream = persons.stream().filter(e->  e.getAge() <= 35);
        stream.forEach(e -> System.out.println(e.getAge()));
    }

    /**
     * stream
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
        Integer i = Stream.of(6, 1, 7, 9, 3).reduce(1, Integer::sum);
    }

    @Test
    public void testMatch() {
        Predicate<Integer> tester = i -> i > 5;
        List<Integer> list = Arrays.asList(1, 1, 6, 7);
        boolean a = list.stream().allMatch(tester);
        boolean b = list.stream().anyMatch(tester);
        boolean c = list.stream().noneMatch(tester);

        int e = list.stream().findFirst().get();
        int f = list.stream().findAny().get();

        long count = list.stream().count();

        int g = list.stream().max((x, y) -> y - x).get();
        int h = list.stream().min((x, y) -> y - x).get();
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
        Map<String, Integer> a = wordCounts.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        //降序
        Map<String, Integer> b = wordCounts.entrySet().stream().sorted((Map.Entry.<String, Integer>comparingByValue().reversed())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        //升序
        Map<String, Integer> c = wordCounts.entrySet().stream().sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        //降序
        Map<String, Integer> d = wordCounts.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Test
    public void mapm() {
        //从对象列表中提取某一列
        Person2 p1 = new Person2("Lord of the rings", 8.8);
        Person2 p2 = new Person2("Back to the future", 8.5);
        Person2 p3 = new Person2("Pulp fiction", 8.9);
        List<Person2> list1 = Arrays.asList(p1, p2, p3);
        Function<Person2, String> getName = Person2::getName;

        List<Integer> list2 = Arrays.asList(6, 1, 7, 9, 3);
        Function<Integer, JSONObject> mapper = e -> {
            JSONObject json = new JSONObject();
            json.put("k1", e);
            json.put("k2", e+"-");
            return json;
        };

        List<String> pss = list1.stream().map(getName).collect(Collectors.toList());
        List<JSONObject> collect = list2.stream().map(mapper).collect(Collectors.toList());
    }

}
