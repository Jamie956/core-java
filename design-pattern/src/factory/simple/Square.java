package factory.simple;

public class Square implements Shape {
    public Square() {
    }

    @Override
    public void draw() {
        System.out.println("Draw Square");
    }
}
