package com.jamie;

import com.alibaba.fastjson.JSONObject;
import com.jamie.entity.Person;
import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Main {
    /**
     * double 比较器 排序
     */
    @Test
    public void comparator() {
        Person p1 = new Person("Lord of the rings", 8.8);
        Person p2 = new Person("Back to the future", 8.5);
        Person p3 = new Person("Pulp fiction", 8.9);
        List<Person> ps = Arrays.asList(p1, p2, p3);
        Comparator<Person> comparator = Comparator.comparingDouble(Person::getLength).reversed();
        ps.sort(comparator);
        ps.forEach(System.out::println);
    }

    /**
     * Predicate< T >	接收T对象，返回boolean
     */
    @Test
    public void predicate() {
        Predicate<Integer> p1 = i -> i > 5;
        Predicate<Integer> p2 = i -> i < 20;
        Predicate<Integer> p3 = i -> i % 2 == 0;

        boolean a0 = p1.test(6);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        //(i > 5) && (i < 20) && (i % 2 == 0)
        Predicate<Integer> f1 = p1.and(p2).and(p3);
        //(i > 5) && (i < 20) && (i % 2 != 0)
        Predicate<Integer> f2 = p1.and(p2).and(p3.negate());
        //(i > 5) && (i < 20) && (i == 7)
        Predicate<Integer> f3 = p1.and(p2).and(p3.negate()).and(Predicate.isEqual(7));

        List test = numbers.stream().filter(f1).collect(Collectors.toList());
        List test2 = numbers.stream().filter(f2).collect(Collectors.toList());
        List test3 = numbers.stream().filter(f3).collect(Collectors.toList());
    }

    /**
     * Consumer< T >	接收T对象，不返回
     */
    @Test
    public void consumer() {
        Consumer<String> c1 = message -> {
            System.out.print("name:" + message.split(",")[0] + ", ");
        };
        Consumer<String> c2 = message -> {
            System.out.println("sex:" + message.split(",")[1]);
        };
        c1.andThen(c2).accept("a,m");
    }

    /**
     * BiConsumer< T, V > 接收T对象, 接收V对象, 无返回
     */
    @Test
    public void biConsumer() {
        BiConsumer<Integer, Integer> biConsumer = (a, b) -> System.out.println(a + b);
        biConsumer.accept(1, 1);
    }

    /**
     * Function< T, R >	接收T对象，返回R对象
     */
    @Test
    public void function() {
        Function<Integer, String> mapper = i -> i + "...";
        String data = mapper.apply(111);
    }

    /**
     * Supplier< T > 返回T
     */
    @Test
    public void supplier() {
        Supplier<String> uuid = () -> UUID.randomUUID().toString();
        String s = uuid.get();
    }

    /**
     * 收集器 Collectors
     */
    @Test
    public void testCollect() {
        //转list
        List<Integer> a0 = Stream.of(1, 4, 6, 8).collect(Collectors.toList());
        //转map
        Map<String, String> a = Stream.of(1, 4, 7, 9).collect(Collectors.toMap(i -> i + "key", i -> i + "value"));
        //分组
        Map<String, List<Integer>> b = Stream.of(1, 4, 7, 9).collect(Collectors.groupingBy(i -> i > 5 ? "> 5" : "< 5"));
        //一进一出
        List<Integer> b1 = Stream.of(1, 4, 7, 9).collect(Collectors.mapping(e -> e + 1, Collectors.toList()));
        //join 成字符串
        String c = Stream.of("a", "b", "c").collect(Collectors.joining());
        String d = Stream.of("a", "b", "c").collect(Collectors.joining(","));
        String e = Stream.of("a", "b", "c").collect(Collectors.joining(",", "{", "}"));
    }

    /**
     * stream
     */
    @Test
    public void testStream() {
        //过滤符合条件的数据
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
        Person p1 = new Person("Lord of the rings", 8.8);
        Person p2 = new Person("Back to the future", 8.5);
        Person p3 = new Person("Pulp fiction", 8.9);
        List<Person> list1 = Arrays.asList(p1, p2, p3);
        Function<Person, String> getName = Person::getName;

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

    @Test
    public void testPCF() {
        List<Integer> list = Arrays.asList(1, 2, 5, 6);
        Predicate<Integer> tester = i -> i > 3;
        Function<Integer, Integer> mapper = i -> i + 1;
        Consumer<Integer> block = System.out::println;
        processElements(list, tester, mapper, block);
    }

    public static <X, Y> void processElements(Iterable<X> source, Predicate<X> tester, Function<X, Y> mapper, Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

}
