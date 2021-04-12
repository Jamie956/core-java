package com.jamie.enum1;

public enum Color {
    /**
     * Color(name, value)
     */
    RED("1", "红色"), GREEN("2", "绿色"), BLANK("3", "白色"), YELLOW("4", "黄色");

    /**
     * 成员变量
     */
    private final String code;
    private final String chinese;

    Color(String code, String chinese) {
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
        for (Color value : Color.values()) {
            System.out.println(value.name() + " " + value.getCode() + " " + value.getChinese());
        }

        //根据枚举名字，获取指定的枚举实例
        Color a = Color.valueOf("RED");
        System.out.println(a);
        Color c = Color.RED;
        System.out.println(c);
        //获取枚举的索引
        int b = Color.valueOf("YELLOW").ordinal();
        System.out.println(b);
    }

}
