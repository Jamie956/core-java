package cat;

public class IDGenerator {
    private static Sequence sequence = new Sequence();
    public static long nextId(){
        return sequence.nextId();
    }
}
