package com.cat.design.pattern.filter;

import java.util.ArrayList;
import java.util.List;


/**
 * 实现过滤接口的类
 */
public class CriteriaMale implements Criteria {
    /**
     * 实现接口的方法：过滤非MALE
     */
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