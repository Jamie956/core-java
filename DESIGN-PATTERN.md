





## 适配器模式

```java
// 适配器模式
// https://www.runoob.com/design-pattern/adapter-pattern.html
public class Client {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}
```

基于行为路由到适配器

```java
public class AudioPlayer implements MediaPlayer {
    // 适配器路由
    @Override
    public void play(String audioType, String fileName) {
        switch (audioType) {
            case "mp3":
                System.out.println("mp3 play " + fileName);
                break;
            case "vlc":
            case "mp4":
                new MediaAdapter(audioType).play(audioType, fileName);
                break;
            default:
                System.out.println("Not support " + audioType);
        }
    }
}
```

适配器路由目标对象和路由行为

```java
public class MediaAdapter implements MediaPlayer {
    AdvanceMediaPlayer play;

    public MediaAdapter(String audioType) {
        switch (audioType) {
            case "vlc":
                play = new VlcPlayer();
                break;
            case "mp4":
                play = new Mp4Player();
                break;
            default:
                break;
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        switch (audioType) {
            case "vlc":
                play.playByVlc(fileName);
                break;
            case "mp4":
                play.playByMp4(fileName);
                break;
            default:
                break;
        }
    }
}
```

适配器有目标对象行为的能力

```java
interface MediaPlayer {
    void play(String audioType, String fileName);
}
```

具体行为接口

```java
interface AdvanceMediaPlayer {
    void playByVlc(String fileName);
    void playByMp4(String fileName);
}
```



```java
public class Mp4Player implements AdvanceMediaPlayer {
    @Override
    public void playByVlc(String fileName) {
    }

    @Override
    public void playByMp4(String fileName) {
        System.out.println("mp4 play " + fileName);
    }
}
```



```java
public class VlcPlayer implements AdvanceMediaPlayer {
    @Override
    public void playByVlc(String fileName) {
        System.out.println("vlc play " + fileName);
    }

    @Override
    public void playByMp4(String fileName) {
    }
}
```



## 桥接模式

调用者决定代理目标对象/行为/目标对象变量

```java
// 桥接
// https://www.runoob.com/design-pattern/bridge-pattern.html
public class Client {
    public static void main(String[] args) {
        new Circle(100, new RedCircle()).draw();
        new Circle(101, new RedCircle()).draw();
        new Circle(100, new GreenCircle()).draw();
        new Circle(101, new GreenCircle()).draw();
    }
}
```

代理目标对象/行为/目标对象变量

```java
public class Circle extends Shape {
    private final int r;

    protected Circle(int r, DrawAPI target) {
        super(target);
        this.r = r;
    }

    //代理执行目标对象的行为
    @Override
    void draw() {
        target.draw(r);
    }
}
```

代理的抽象

```java
public abstract class Shape {
    //目标对象
    protected DrawAPI target;

    protected Shape(DrawAPI target) {
        this.target = target;
    }

    //目标对象行为
    abstract void draw();
}
```

被代理的对象

```java
public class GreenCircle implements DrawAPI {
    @Override
    public void draw(int r) {
        System.out.println("Draw Green Circle r=" + r);
    }
}
public class RedCircle implements DrawAPI {
    @Override
    public void draw(int r) {
        System.out.println("Draw Red Circle r=" + r);
    }
}
```



```java
interface DrawAPI {
    void draw(int x);
}
```



## 建造者模式



```java
/**
 * 建造者模式
 * https://www.runoob.com/design-pattern/builder-pattern.html
 */
public class Client {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal meal = mealBuilder.prepareVegMeal();
        System.out.println("VegMeal:");
        meal.showItems();
        System.out.println("total:" + meal.getCost());

        System.out.println();
        Meal meal1 = mealBuilder.prepareNonVegMeal();
        System.out.println("VegMeal:");
        meal1.showItems();
        System.out.println("total:" + meal1.getCost());
    }
}
```



建造者搭配组合多个 Item 对象

```java
// builder 组合对象构建
public class MealBuilder {
    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
```



```java
// Item 容器
public class Meal {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float getCost() {
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems() {
        for (Item item : items) {
            System.out.printf("%s (%s) -------- %s %n", item.name(), item.packing().pack(), item.price());
        }
    }
}
```



```java
interface Item {
    String name();
    Packing packing();
    float price();
}

public abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }
    @Override
    public abstract float price();
}
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "Chicken Burger";
    }
    @Override
    public float price() {
        return 50.0f;
    }
}
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "Veg Burger";
    }
    @Override
    public float price() {
        return 25.0f;
    }
}

public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }
    @Override
    public float price() {
        return 30.0f;
    }
}
public class Pepsi extends ColdDrink {
    @Override
    public String name() {
        return "Pepsi";
    }
    @Override
    public float price() {
        return 35.0f;
    }
}

```



```java
interface Packing {
    String pack();
}
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}

```



## 装饰器模式

```java
/**
 * 装饰器模式：不改变结构，向一个现有对象添加新的功能
 * https://www.runoob.com/design-pattern/decorator-pattern.html
 */
public class Client {
    public static void main(String[] args) {
        Circle circle = new Circle();
        ConcreteDecorator circleWithDecorate = new ConcreteDecorator(circle);
        circleWithDecorate.draw();
    }
}
```



```java
// 装饰器代理目标类行为
public class ConcreteDecorator extends AbstractDecorator {
    ConcreteDecorator(Shape target) {
        super(target);
    }

    @Override
    public void draw() {
        target.draw();
        System.out.println("do decorate...");
    }
}

// 装饰器的抽象，与目标对象实现相同的接口，有相同的行为
public abstract class AbstractDecorator implements Shape {
    protected Shape target;

    AbstractDecorator(Shape target) {
        this.target = target;
    }

    @Override
    public void draw() {
        target.draw();
    }
}
```



```java
public class Circle implements Shape {
    public Circle() {
    }

    @Override
    public void draw() {
        System.out.println("draw circle");
    }
}

interface Shape {
    void draw();
}

```



## 外观模式



```java
/**
 * 外观模式（Facade Pattern）
 * https://www.runoob.com/design-pattern/facade-pattern.html
 */
public class Client {
    public static void main(String[] args) {
        ShapeMaker s = new ShapeMaker();
        s.drawCircle();
        s.drawRectangle();
    }
}

```

创建多个代理对象，代理对象行为

```java
// 创建多个代理对象，代理对象行为
public class ShapeMaker {
    private final Shape circle;
    private final Shape rectangle;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
    }
    public void drawCircle() {
        circle.draw();
    }
    public void drawRectangle() {
        rectangle.draw();
    }
}
```

目标对象

```java
public class Rectangle implements Shape {
    public Rectangle() {
    }

    @Override
    public void draw() {
        System.out.println("Draw Rectangle");
    }
}

public class Circle implements Shape {
    public Circle() {
    }

    @Override
    public void draw() {
        System.out.println("Draw Circle");
    }
}

interface Shape {
    void draw();
}
```



## 工厂模式

```java
/**
 * 工厂模式
 * https://www.runoob.com/design-pattern/factory-pattern.html
 */
public class Client {
    public static void main(String[] args) {
        Factory factory = new Factory();

        Shape square = factory.get("Square");
        square.draw();

        Shape circle = factory.get("Circle");
        circle.draw();
    }
}
```



```java
public class Factory {
    // 根据 key 由工厂创建实例
    public Shape get(String key) {
        switch (key) {
            case "Square":
                return new Square();
            case "Circle":
                return new Circle();
            default:
                break;
        }
        return null;
    }
}
```



```java
interface Shape {
    void draw();
}
public class Square implements Shape {
    public Square() {
    }

    @Override
    public void draw() {
        System.out.println("Draw Square");
    }
}
public class Circle implements Shape {
    public Circle() {
    }

    @Override
    public void draw() {
        System.out.println("Draw Circle");
    }
}
```



## 过滤器模式

```java
/**
 * 过滤器模式（Filter Pattern）
 * 使用不同的标准来过滤一组对象，通过逻辑运算以解耦的方式把它们连接起来
 * https://www.runoob.com/design-pattern/filter-pattern.html
 */
public class Client {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Robert", "Male", "Single"));
        persons.add(new Person("John", "Male", "Married"));
        persons.add(new Person("Laura", "Female", "Married"));
        persons.add(new Person("Diana", "Female", "Single"));
        persons.add(new Person("Mike", "Male", "Single"));
        persons.add(new Person("Bobby", "Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleAndMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        System.out.println(male.meetCriteria(persons));
        System.out.println(female.meetCriteria(persons));
        System.out.println(singleAndMale.meetCriteria(persons));
        System.out.println(singleOrFemale.meetCriteria(persons));
    }
}
```



```java
interface Criteria {
    List<Person> meetCriteria(List<Person> persons);
}
public class CriteriaFemale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> items) {
        List<Person> result = new ArrayList<>();
        for (Person item : items) {
            if ("FEMALE".equalsIgnoreCase(item.getGender())) {
                result.add(item);
            }
        }
        return result;
    }
}
public class CriteriaMale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> items) {
        List<Person> result = new ArrayList<>();
        for (Person item : items) {
            if ("MALE".equalsIgnoreCase(item.getGender())) {
                result.add(item);
            }
        }
        return result;
    }
}
public class CriteriaSingle implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> items) {
        List<Person> result = new ArrayList<>();
        for (Person item : items) {
            if ("SINGLE".equalsIgnoreCase(item.getMaritalStatus())) {
                result.add(item);
            }
        }
        return result;
    }
}
public class AndCriteria implements Criteria {
    private Criteria c1;
    private Criteria c2;

    public AndCriteria(Criteria c1, Criteria c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return c2.meetCriteria(c1.meetCriteria(persons));
    }
}
public class OrCriteria implements Criteria {
    private Criteria c1;
    private Criteria c2;

    public OrCriteria(Criteria c1, Criteria c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    // 求并集
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
```



## 享元模式

```java
/**
 * 享元模式（Flyweight Pattern）：减少创建对象的数量，减少内存占用，提高性能
 * https://www.runoob.com/design-pattern/flyweight-pattern.html
 */
public class Client {
    public static void main(String[] args) {
        Shape c1 = Factory.get("Red");
        Shape c2 = Factory.get("Red");
        Shape c3 = Factory.get("Black");

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }
}
```



```java
public class Factory {
    // Map缓存实例
    private static final HashMap<String, Shape> MAP = new HashMap<>();

    public static Shape get(String key) {
        Shape target = MAP.get(key);
        if (target == null) {
            target = new Circle();
            MAP.put(key, target);
        }
        return target;
    }
}
```



```java
interface Shape {
    void draw();
}
public class Circle implements Shape {
    public Circle() {}

    @Override
    public void draw() {
        System.out.println("draw");
    }
}
```



## 原型模式

```java
/**
 * 原型模式（Prototype Pattern）：创建重复的对象
 * https://www.runoob.com/design-pattern/prototype-pattern.html
 */
public class Client {
    public static void main(String[] args) {
        Prototype.load();

        Shape s1 = Prototype.copy("rectangle");
        Shape s2 = Prototype.copy("rectangle");

        Shape s3 = Prototype.copy("circle");
        Shape s4 = Prototype.copy("circle");
    }
}
```



```java
public class Prototype {
    private static Hashtable<String, Shape> SHAPE_MAP = new Hashtable<>();

    //克隆缓存对象
    public static Shape copy(String shapeId) {
        Shape cacheShape = SHAPE_MAP.get(shapeId);
        return (Shape)cacheShape.clone();
    }

    public static void load() {
        SHAPE_MAP.put("rectangle", new Rectangle());
        SHAPE_MAP.put("circle", new Circle());
    }
}
```



```java
public abstract class Shape implements Cloneable {
    abstract void draw();

    // 实现克隆方法
    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
public class Circle extends Shape {
    public Circle() {
    }

    @Override
    void draw() {
        System.out.println("draw Circle");
    }
}
public class Rectangle extends Shape {
    public Rectangle() {
    }

    @Override
    void draw() {
        System.out.println("draw Rectangle");
    }
}
```



## 单例模式

```java
// 饿汉式，线程安全
public class EagerSingleton {
    // 静态变量，创建实例时就实例化对象
    private final static Single INSTANCE = new Single();
    // 私有构造，外部类无法创建实例
    private EagerSingleton() {
    }
    // 静态方法，返回实例对象
    public static Single getInstance() {
        return INSTANCE;
    }
}
```



```java
// 懒汉式-加锁，线程安全
public class LazySingleton {
    // 静态变量
    private static Single INSTANCE;
    // 私有构造，不允许外部实例化
    private LazySingleton() {
    }
    // 获取实例，实例不存在时创建
    // 对象锁，不允许其他线程修改此对象
    public static synchronized Single getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Single();
        }
        return INSTANCE;
    }
}
```

