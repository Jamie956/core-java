package com.jamie.design.pattern.filter;

import java.util.List;

/**
 * 定义过滤接口
 */
interface Criteria {
    /**
     * 过滤接口方法，由实现类实现
     */
    List<Person> meetCriteria(List<Person> persons);
}
