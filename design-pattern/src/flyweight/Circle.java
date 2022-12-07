package flyweight;

public class Circle implements Shape {
    //缓存key，用于查找
    private String key;

    public Circle(String key) {
        this.key = key;
    }

    @Override
    public void draw() {
        System.out.println("draw");
    }
}
