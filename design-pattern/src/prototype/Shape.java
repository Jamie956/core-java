package prototype;

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