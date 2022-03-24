package filter;

import java.util.List;

interface Criteria {
    /**
     * 过滤：输入List，输出List
     */
    List<Person> meetCriteria(List<Person> persons);
}
