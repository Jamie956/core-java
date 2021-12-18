package com.jamie.design.pattern.filter;

import java.util.List;

/**
 * 过滤器的实现类
 */
public class OrCriteria implements Criteria {
    private Criteria firstFilter;
    private Criteria secondFilter;

    /**
     * 构造入参两个过滤器
     */
    public OrCriteria(Criteria firstFilter, Criteria secondFilter) {
        this.firstFilter = firstFilter;
        this.secondFilter = secondFilter;
    }

    /**
     * 实现过滤器接口的方法，求并集
     */
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