package com.lang;

import org.junit.Assert;

public enum ColorEnum {
    // 定义枚举对象
    RED("1", "红色"),
    GREEN("2", "绿色"),
    BLANK("3", "白色"),
    YELLOW("4", "黄色");

    private final String code;
    private final String chinese;

    ColorEnum(String code, String chinese) {
        this.code = code;
        this.chinese = chinese;
    }

    public String getCode() {
        return code;
    }

    public String getChinese() {
        return chinese;
    }

    public static void main(String[] args) {
        // 获取全部枚举对象
        ColorEnum[] values = ColorEnum.values();
        Assert.assertEquals("1", values[0].getCode());
        Assert.assertEquals("红色", values[0].getChinese());

        // 获取枚举索引
        int index = values[0].ordinal();
        Assert.assertEquals(0, index);

        // 根据名字获取枚举对象
        ColorEnum red1 = ColorEnum.valueOf("RED");
        Assert.assertEquals("1", red1.getCode());
        Assert.assertEquals("红色", red1.getChinese());

        ColorEnum red2 = ColorEnum.RED;
        Assert.assertEquals("1", red2.getCode());
        Assert.assertEquals("红色", red2.getChinese());
    }
}
