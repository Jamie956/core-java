package com.jamie;

import com.jamie.entity.Person;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Main {

    /**
     * 对象排序
     */
    @Test
    public void orderingDouble() {
        List<Person> ps = Arrays.asList(new Person("Lord of the rings", 8.8), new Person("Back to the future", 8.5), new Person("Carlito's way", 7.9), new Person("Pulp fiction", 8.9));
        ps.sort(Comparator.comparingDouble(Person::getLength).reversed());
        ps.forEach(System.out::println);
    }

    /**
     * Predicate
     * 值是 lambda 表达式，泛型指定参数类型，返回boolean
     * test() 传入参数，调 lambda表达式，返回boolean
     */
    @Test
    public void testPredicate() {
        Predicate<Integer> tester = (Integer i) -> i > 10;
        boolean ret = tester.test(11);
    }

    /**
     * Consumer
     * 值是 lambda 表达式，泛型指定参数类型，无返回
     * accept() 传入参数，调 lambda表达式，无返回
     */
    @Test
    public void testConsumer() {
        Consumer<String> block = System.out::println;
        block.accept("halo");
    }

    /**
     * BiConsumer
     * 值是 lambda 表达式，泛型指定2个参数类型，无返回
     * accept() 传入2个参数，调 lambda表达式，无返回
     */
    @Test
    public void testBiConsumer() {
        BiConsumer<Integer, String> biConsumer = (a, b) -> System.out.println(a + b);
        biConsumer.accept(0, "1");
    }

    /**
     * Function
     * 值是 lambda 表达式，泛型指定参数类型和返回类型，有返回
     * apply() 传入参数，调 lambda表达式，返回值
     */
    @Test
    public void testFunction() {
        Function<Integer, String> mapper = i -> i + "...";
        String data = mapper.apply(111);
    }

    public static <X, Y> void processElements(Iterable<X> source, Predicate<X> tester, Function<X, Y> mapper, Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    /**
     * 泛型
     */
    @Test
    public void testPCF() {
        List<Integer> list = Arrays.asList(1, 2, 5, 6);
        Predicate<Integer> tester = i -> i > 3;
        Function<Integer, Integer> mapper = i -> i + 1;
        Consumer<Integer> block = System.out::println;
        processElements(list, tester, mapper, block);
    }

    /**
     * 收集器
     * groupingBy    mapper 返回结果作为组名，Collectors 收集符合条件的元素
     */
    @Test
    public void testCollect() {
        //toList        将流收集成集合
        List<Integer> a0 = Stream.of(1, 4, 6, 8).collect(Collectors.toList());
        //toMap     将流收集成map
        Map<String, String> a = Stream.of(1, 4, 7, 9).collect(Collectors.toMap(i -> i + "key", i -> i + "value"));
        //groupingBy        流分组收集
        Map<String, List<Integer>> b = Stream.of(1, 4, 7, 9).collect(Collectors.groupingBy(i -> i > 5 ? "> 5" : "< 5"));
        //mapping       将流元素处理后交给下游
        List<Integer> b1 = Stream.of(1, 4, 7, 9).collect(Collectors.mapping(e -> e + 1, Collectors.toList()));
        //joining       join 流元素
        String c = Stream.of("a", "b", "c").collect(Collectors.joining());
        String d = Stream.of("a", "b", "c").collect(Collectors.joining(","));
        String e = Stream.of("a", "b", "c").collect(Collectors.joining(",", "{", "}"));
    }

    /**
     * stream 元素控制处理
     */
    @Test
    public void testStream() {
        //过滤符合条件的数据
        List<Integer> a = Stream.of(1, 5, 6, 7).filter(i -> i > 5).collect(Collectors.toList());
        //元素去重
        List<Integer> b = Stream.of(1, 1, 6, 7).distinct().collect(Collectors.toList());
        //取偏移的流元素
        List<Integer> c = Stream.of(1, 1, 6, 7).limit(2).collect(Collectors.toList());
        //流处理，得到处理后的元素
        List<Integer> d = Stream.of(1, 1, 6, 7).map(i -> i + 1).collect(Collectors.toList());
        //流元素排序
        List<Integer> e = Stream.of(6, 1, 7, 9, 3).sorted().collect(Collectors.toList());
        //跳过前n个元素
        List<Integer> f = Stream.of(6, 1, 7, 9, 3).skip(2).collect(Collectors.toList());
        //迭代得到元素
        List<Integer> g = Stream.iterate(0, n -> n + 2).limit(10).collect(Collectors.toList());
        //生成随机数
        List<Double> h = Stream.generate(Math::random).limit(10).collect(Collectors.toList());
        //总和
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
    public void asd() {
        //从对象列表中提取一列(以name为例)
//        List<String> nameList = studentList.stream().map(StudentInfo::getName).collect(Collectors.toList());
    }

}
