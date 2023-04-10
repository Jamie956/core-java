package filter;

import java.util.List;

interface Criteria {
    List<Person> meetCriteria(List<Person> persons);
}
