package filter;

import java.util.List;

public class OrCriteria implements Criteria {
    private Criteria c1;
    private Criteria c2;

    public OrCriteria(Criteria c1, Criteria c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    /**
     * 并集
     */
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> l1 = c1.meetCriteria(persons);
        List<Person> l2 = c2.meetCriteria(l1);
        for (Person e : l2) {
            if (!l1.contains(e)) {
                l1.add(e);
            }
        }
        return l1;
    }
}