package factory.reflection;

public class Rectangle implements Shape {
    public Rectangle() {
    }

    @Override
    public void draw() {
        System.out.println("Draw Rectangle");
    }
}
