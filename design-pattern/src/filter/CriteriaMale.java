package filter;

import java.util.ArrayList;
import java.util.List;

public class CriteriaMale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> items) {
        List<Person> result = new ArrayList<>();
        for (Person item : items) {
            if ("MALE".equalsIgnoreCase(item.getGender())) {
                result.add(item);
            }
        }
        return result;
    }
}