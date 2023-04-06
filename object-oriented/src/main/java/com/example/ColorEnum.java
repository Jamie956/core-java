package com.example;

public enum ColorEnum {
    // 定义枚举对象
    RED("1", "红色"), GREEN("2", "绿色"), BLANK("3", "白色"), YELLOW("4", "黄色");

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

        // 根据名字获取枚举对象
        ColorEnum red1 = ColorEnum.valueOf("RED");
        ColorEnum red2 = ColorEnum.RED;

        // 获取枚举索引
        int index = red1.ordinal();
    }

}
