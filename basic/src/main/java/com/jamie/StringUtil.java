package com.jamie;

public class StringUtil {
    public static void main(String[] args) {
        String replace = cleanLineBeak("   \tname\r\n                              ");
        System.out.println(replace);
    }

    public static String cleanLineBeak(String str) {
        return str.replaceAll("[\n\t\r]","").trim();
    }

}
