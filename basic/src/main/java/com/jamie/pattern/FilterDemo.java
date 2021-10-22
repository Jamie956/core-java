package com.jamie.pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器模式（Filter Pattern）或标准模式（Criteria Pattern）是一种设计模式，这种模式允许开发人员使用不同的标准来过滤一组对象，通过逻辑运算以解耦的方式把它们连接起来。这种类型的设计模式属于结构型模式，它结合多个标准来获得单一标准。
 */
public class FilterDemo {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Robert", "Male", "Single"));
        persons.add(new Person("John", "Male", "Married"));
        persons.add(new Person("Laura", "Female", "Married"));
        persons.add(new Person("Diana", "Female", "Single"));
        persons.add(new Person("Mike", "Male", "Single"));
        persons.add(new Person("Bobby", "Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleAndMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        System.out.println(male.meetCriteria(persons));
        System.out.println(female.meetCriteria(persons));
        System.out.println(singleAndMale.meetCriteria(persons));
        System.out.println(singleOrFemale.meetCriteria(persons));

    }

    @AllArgsConstructor
    @Getter
    @ToString
    static class Person {
        private String name;
        private String gender;
        private String maritalStatus;
    }

    interface Criteria {
        List<Person> meetCriteria(List<Person> persons);
    }

    /**
     * 过滤出 gender=MALE 的对象
     */
    static class CriteriaMale implements Criteria {
        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> malePersons = new ArrayList<>();
            for (Person person : persons) {
                if ("MALE".equalsIgnoreCase(person.getGender())) {
                    malePersons.add(person);
                }
            }
            return malePersons;
        }
    }

    /**
     * 过滤出 gender=FEMALE 的对象
     */
    static class CriteriaFemale implements Criteria {
        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> femalePersons = new ArrayList<>();
            for (Person person : persons) {
                if ("FEMALE".equalsIgnoreCase(person.getGender())) {
                    femalePersons.add(person);
                }
            }
            return femalePersons;
        }
    }

    /**
     * 过滤出 maritalStatus=SINGLE 的对象
     */
    static class CriteriaSingle implements Criteria {
        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> singlePersons = new ArrayList<>();
            for (Person person : persons) {
                if ("SINGLE".equalsIgnoreCase(person.getMaritalStatus())) {
                    singlePersons.add(person);
                }
            }
            return singlePersons;
        }
    }

    /**
     * and 过滤链
     */
    @AllArgsConstructor
    static class AndCriteria implements Criteria {
        private Criteria firstFilter;
        private Criteria secondFilter;

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> firstFilterResult = firstFilter.meetCriteria(persons);
            return secondFilter.meetCriteria(firstFilterResult);
        }
    }

    /**
     * 并集
     */
    @AllArgsConstructor
    static class OrCriteria implements Criteria {
        private Criteria firstFilter;
        private Criteria secondFilter;

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> firstFilterItems = firstFilter.meetCriteria(persons);
            List<Person> secondFilterItems = secondFilter.meetCriteria(firstFilterItems);
            for (Person secondFilterItem : secondFilterItems) {
                if (!firstFilterItems.contains(secondFilterItem)) {
                    firstFilterItems.add(secondFilterItem);
                }
            }
            return firstFilterItems;
        }
    }

}
