package com.cat.pattern.prototype;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 形状抽象类
 */
@Setter
@Getter
@ToString
public abstract class Shape implements Cloneable {
    private String id;
    protected String type;

    /**
     * 由子类实现的抽象方法
     */
    abstract void draw();

    /**
     * 实现克隆方法
     */
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