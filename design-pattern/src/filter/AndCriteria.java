package filter;

import java.util.List;

public class AndCriteria implements Criteria {
    private Criteria c1;
    private Criteria c2;

    public AndCriteria(Criteria c1, Criteria c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return c2.meetCriteria(c1.meetCriteria(persons));
    }
}
