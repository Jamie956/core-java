package bridge;

public class RedCircle implements DrawAPI {
    @Override
    public void draw(int r) {
        System.out.println("Draw Red Circle r=" + r);
    }
}