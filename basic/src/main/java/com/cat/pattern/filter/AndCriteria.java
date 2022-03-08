package com.cat.pattern.filter;

import java.util.List;

/**
 * 实现接口，and 过滤链
 */
public class AndCriteria implements Criteria {
    private Criteria firstFilter;
    private Criteria secondFilter;

    /**
     * 构造初始两个过滤器
     */
    public AndCriteria(Criteria firstFilter, Criteria secondFilter) {
        this.firstFilter = firstFilter;
        this.secondFilter = secondFilter;
    }

    /**
     * 实现接口的方法，执行构造的那两个过滤器的过滤操作
     */
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstFilterResult = firstFilter.meetCriteria(persons);
        List<Person> secondFilterResult = secondFilter.meetCriteria(firstFilterResult);
        return secondFilterResult;
    }
}
