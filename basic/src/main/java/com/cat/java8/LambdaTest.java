package com.cat.java8;

import lombok.Data;
import org.junit.Test;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class LambdaTest {
    @Data
    private static class Person {
        public enum Sex {
            MALE, FEMALE
        }

        String name;
        LocalDate birthday;
        Sex gender;
        String emailAddress;
        Double length;

        Person(String nameArg, LocalDate birthdayArg, Sex genderArg, String emailArg) {
            name = nameArg;
            birthday = birthdayArg;
            gender = genderArg;
            emailAddress = emailArg;
        }

        Person(String name, Double length) {
            this.name = name;
            this.length = length;
        }

        public int getAge() {
            return birthday.until(IsoChronology.INSTANCE.dateNow()).getYears();
        }

        public void printPerson() {
            System.out.println(name + ", " + this.getAge());
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

    /**
     * predicate filter
     */
    @Test
    public void printPersonsOlderThan() {
        Predicate<Person> predicate = e -> e.getAge() >= 20;
        Person.createRoster().stream().filter(predicate).forEach(Person::printPerson);
    }

    /**
     * double 比较器 降序排序
     */
    @Test
    public void testComparator() {
        Person p1 = new Person("Lord of the rings", 8.8);
        Person p2 = new Person("Back to the future", 8.5);
        Person p3 = new Person("Pulp fiction", 8.9);

        List<Person> collect = Arrays.asList(p1, p2, p3).stream().sorted(Comparator.comparingDouble(Person::getLength).reversed()).collect(Collectors.toList());
    }

    interface CheckPerson {
        boolean test(Person p);
    }

    /**
     * 非匿名类 bool方法 作为 predicate
     */
    @Test
    public void classAsPredict(){
        class CheckAgeBetween implements CheckPerson {
            @Override
            public boolean test(Person p) {
                return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
            }
        }

        CheckPerson check = new CheckAgeBetween();
        Person.createRoster().stream().filter(check::test).forEach(Person::printPerson);
    }

    /**
     * 匿名类 bool方法 作为 predicate
     */
    @Test
    public void synClassAsPredict() {
        CheckPerson check = new CheckPerson() {
            @Override
            public boolean test(Person p) {
                return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
            }
        };
        Person.createRoster().stream().filter(check::test).forEach(Person::printPerson);
    }

    /**
     * lambda predicate 作为参数
     */
    @Test
    public void lambdaAsPredict() {
        Predicate<Person> predicate = p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
        Person.createRoster().stream().filter(predicate).forEach(Person::printPerson);
    }

    /**
     * 各函数配合使用
     */
    @Test
    public void compose() {
        Predicate<Person> tester = p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
        Function<Person, String> mapper = Person::getEmailAddress;
        Consumer<String> block = System.out::println;

        Person.createRoster().stream().filter(tester).forEach(e -> {
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

    /**
     * 使用泛型 各函数配合使用
     */
    @Test
    public void compose2() {
        processElements(Person.createRoster(),
                p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25,
                Person::getEmailAddress,
                System.out::println);
    }

    @Test
    public void compose3() {
        List<Integer> list = Arrays.asList(1, 2, 5, 6);
        Predicate<Integer> tester = i -> i > 3;
        Function<Integer, Integer> mapper = i -> i + 1;
        Consumer<Integer> block = System.out::println;
        processElements(list, tester, mapper, block);
    }

    public static
    <T, SOURCE extends Collection<T>, DEST extends Collection<T>>
    DEST
    transferElements(SOURCE sourceCollection, Supplier<DEST> collectionFactory) {
        DEST result = collectionFactory.get();
        for (T t : sourceCollection) {
            result.add(t);
        }
        return result;
    }

    @Test
    public void transferElementsTest() {
        Set<Person> a = transferElements(Person.createRoster(), HashSet::new);
    }

}
