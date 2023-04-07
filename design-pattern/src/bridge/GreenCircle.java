package bridge;

public class GreenCircle implements DrawAPI {
    @Override
    public void draw(int r) {
        System.out.println("Draw Green Circle r=" + r);
    }
}
