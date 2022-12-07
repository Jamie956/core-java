package decorator;

public class Circle implements Shape {
    public Circle() {
    }

    @Override
    public void draw() {
        System.out.println("draw circle");
    }
}
