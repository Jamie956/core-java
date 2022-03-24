package decorator;

/**
 * 实现类，实现行为
 */
public class Circle implements Shape {
    public Circle() {
    }

    @Override
    public void draw() {
        System.out.println("draw circle");
    }
}
