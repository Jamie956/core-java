package com.jamie.java8;

import org.junit.Test;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class LambdaTest {
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

    public static void printPersonsOlderThan(List<Person> roster, int age) {
        roster.stream().filter(e -> e.getAge() >= age).forEach(Person::printPerson);
    }


    public static void printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
        roster.stream().filter(e -> low <= e.getAge() && e.getAge() < high).forEach(Person::printPerson);
    }

    interface CheckPerson {
        boolean test(Person p);
    }

    public static void printPersons(List<Person> roster, CheckPerson tester) {
        roster.stream().filter(tester::test).forEach(Person::printPerson);
    }

    public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
        roster.stream().filter(tester).forEach(Person::printPerson);
    }

    public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
        roster.stream().filter(tester).forEach(block);
    }

    public static void processPersonsWithFunction(List<Person> roster, Predicate<Person> tester, Function<Person, String> mapper, Consumer<String> block) {
        roster.stream().filter(tester).forEach(e -> {
            String data = mapper.apply(e);
            block.accept(data);
        });
    }

    public static <X, Y> void processElements(Iterable<X> source, Predicate<X> tester, Function<X, Y> mapper, Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>> DEST transferElements(SOURCE sourceCollection, Supplier<DEST> collectionFactory) {
        DEST result = collectionFactory.get();
        for (T t : sourceCollection) {
            result.add(t);
        }
        return result;
    }

    /**
     * double 比较器 降序排序
     */
    public static <T> List<T> compareObjectDoubleDesc(List<T> list, ToDoubleFunction<? super T> keyExtractor) {
        return list.stream().sorted(Comparator.comparingDouble(keyExtractor).reversed()).collect(Collectors.toList());
    }
    /**
     * double 比较器 升序排序
     */
    public static <T> List<T> compareObjectDoubleAsc(List<T> list, ToDoubleFunction<? super T> keyExtractor) {
        return list.stream().sorted(Comparator.comparingDouble(keyExtractor)).collect(Collectors.toList());
    }

    @Test
    public void testComparator() {
        com.jamie.entity.Person p1 = new com.jamie.entity.Person("Lord of the rings", 8.8);
        com.jamie.entity.Person p2 = new com.jamie.entity.Person("Back to the future", 8.5);
        com.jamie.entity.Person p3 = new com.jamie.entity.Person("Pulp fiction", 8.9);
        List<com.jamie.entity.Person> ps = Arrays.asList(p1, p2, p3);
        List<com.jamie.entity.Person> people = compareObjectDoubleDesc(ps, com.jamie.entity.Person::getLength);
        List<com.jamie.entity.Person> people2 = compareObjectDoubleAsc(ps, com.jamie.entity.Person::getLength);
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
    public void testPredicate() {
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

    public static void main(String[] args) {
        List<Person> roster = Person.createRoster();

        System.out.println("age > 20:");
        printPersonsOlderThan(roster, 20);
        System.out.println();

        System.out.println("14 < ages < 30:");
        printPersonsWithinAgeRange(roster, 14, 30);
        System.out.println();

        class CheckPersonEligibleForSelectiveService implements CheckPerson {
            @Override
            public boolean test(Person p) {
                return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
            }
        }

        System.out.println("非匿名类作为参数");
        printPersons(roster, new CheckPersonEligibleForSelectiveService());
        System.out.println();

        System.out.println("匿名类作为参数");
        printPersons(roster, new CheckPerson() {
            @Override
            public boolean test(Person p) {
                return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
            }
        });
        System.out.println();

        System.out.println("lambda作为参数");
        printPersons(roster, (Person p) -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);
        System.out.println();

        System.out.println("使用Predicate");
        printPersonsWithPredicate(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);
        System.out.println();

        System.out.println("使用Predicate 和 Consumer");
        processPersons(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25, Person::printPerson);
        System.out.println();

        System.out.println("使用 Function");
        processPersonsWithFunction(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25, Person::getEmailAddress, System.out::println);
        System.out.println();

        System.out.println("使用 泛型");
        processElements(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25, Person::getEmailAddress, System.out::println);
        System.out.println();

        Set<Person> rosterSetLambda = transferElements(roster, HashSet::new);
        System.out.println();

        //
        List<Integer> list = Arrays.asList(1, 2, 5, 6);
        Predicate<Integer> tester = i -> i > 3;
        Function<Integer, Integer> mapper = i -> i + 1;
        Consumer<Integer> block = System.out::println;
        processElements(list, tester, mapper, block);

    }


}
