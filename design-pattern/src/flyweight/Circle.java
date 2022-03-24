package flyweight;

public class Circle implements Shape {
    private String key;

    public Circle(String key) {
        this.key = key;
    }

    @Override
    public void draw() {
        System.out.println("draw");
    }
}
