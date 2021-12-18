package com.jamie.design.pattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现过滤接口的类
 */
public class CriteriaFemale implements Criteria {
    /**
     * 过滤非FEMALE
     */
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