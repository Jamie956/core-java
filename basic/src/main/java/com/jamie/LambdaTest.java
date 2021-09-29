package com.jamie;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaTest {
    static class Person {
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
        roster.stream().filter(tester).forEach(e-> {
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
    }
}
