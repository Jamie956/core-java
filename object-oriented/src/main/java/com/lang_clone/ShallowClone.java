package com.lang_clone;

import java.io.Serializable;

// 浅克隆，不克隆引用类型的变量
public class ShallowClone {
    public static void main(String[] args) {
        try {
            ShallowCloneObject obj = new ShallowCloneObject();
            ShallowCloneObject clone = (ShallowCloneObject) obj.clone();

            System.out.println(obj == clone);
            System.out.println(obj.sub == clone.sub);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

// 对象实现 Cloneable，重写clone()
class ShallowCloneObject implements Cloneable {
    public ShallowCloneSubObject sub;

    public ShallowCloneObject() {
        this.sub = new ShallowCloneSubObject("a");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }
}

class ShallowCloneSubObject implements Serializable {
    private static final long serialVersionUID = -4537716904357183030L;
    public String s;

    public ShallowCloneSubObject(String s) {
        this.s = s;
    }
}