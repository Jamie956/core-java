package com.jamie.java8;

import lombok.Data;
import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
    @Data
    private static class Person {
        private String name;
        private Double length;

        public Person() {
        }

        public Person(String name) {
            this.name = name;
        }
    }

    @Test
    public void test() {
        //创建一个空Optional
        Optional<Person> optional1 = Optional.empty();
        //判空
        boolean present1 = optional1.isPresent();

        //创建一个 Optional 实例
        Optional<Person> optional2 = Optional.of(new Person());
        boolean present2 = optional2.isPresent();
        Person p = optional2.get();

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Person person1 = optional1.orElse(new Person("11"));
        Person person2 = optional2.orElse(new Person("22"));

        Person person = null;
        //异常 java.lang.NullPointerException
//        Optional<Person> optional3 = Optional.of(person);

        //Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
        Optional<Person> optional4 = Optional.ofNullable(person);
        boolean present = optional4.isPresent();
    }

    @Test
    public void test4() {
        Person person = new Person();
        Optional<Person> op = Optional.ofNullable(person);

        /**
         * 使用 map 从 optional 对象中提取和转换值
         * 如果想提取人员姓名，之前需要判断persion !=null, Optional提供了一个map方法，对其处理
         * map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
         **/
        Optional<String> op2 = op.map(Person::getName);
        System.out.println(op2.get());

        Optional<Double> op3 = op.map(Person::getLength);
        System.out.println(op3.isPresent());

        Optional<String> op4 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(op4.get());
    }

}
