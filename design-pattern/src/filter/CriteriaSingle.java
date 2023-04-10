package filter;

import java.util.ArrayList;
import java.util.List;

public class CriteriaSingle implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> items) {
        List<Person> result = new ArrayList<>();
        for (Person item : items) {
            if ("SINGLE".equalsIgnoreCase(item.getMaritalStatus())) {
                result.add(item);
            }
        }
        return result;
    }
}
