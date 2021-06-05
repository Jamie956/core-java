package com.jamie.pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    @AllArgsConstructor
    static class AndCriteria implements Criteria {

        private Criteria criteria;
        private Criteria otherCriteria;

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
            return otherCriteria.meetCriteria(firstCriteriaPersons);
        }
    }

    @AllArgsConstructor
    static class OrCriteria implements Criteria {

        private Criteria criteria;
        private Criteria otherCriteria;

        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
            List<Person> otherCriteriaPersons = otherCriteria.meetCriteria(firstCriteriaPersons);
            for (Person otherCriteriaPerson : otherCriteriaPersons) {
                if (!firstCriteriaPersons.contains(otherCriteriaPerson)) {
                    firstCriteriaPersons.add(otherCriteriaPerson);
                }
            }
            return firstCriteriaPersons;
        }
    }

}
