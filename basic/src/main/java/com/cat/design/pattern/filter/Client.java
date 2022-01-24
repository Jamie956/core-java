package com.cat.design.pattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器模式（Filter Pattern）或标准模式（Criteria Pattern）
 * 使用不同的标准来过滤一组对象，
 * 通过逻辑运算以解耦的方式把它们连接起来。
 * 属于结构型模式，它结合多个标准来获得单一标准。
 */
public class Client {
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
}
