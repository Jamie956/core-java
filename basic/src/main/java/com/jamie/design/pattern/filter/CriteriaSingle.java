package com.jamie.design.pattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现过滤接口的类
 */
public class CriteriaSingle implements Criteria {
    /**
     * 实现接口的方法：过滤单身狗
     */
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
