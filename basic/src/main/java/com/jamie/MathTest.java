package com.jamie;

import org.junit.Test;

public class MathTest {
    /**
     * 取模
     */
    @Test
    public void mod(){
//        int a = 499 % 500;//499
//        int b = 500 % 500;//0
//        int c = 501 % 500;//1
//        int d = 999 % 500;//499
//        int e = 1000 % 500;//0
//        int f = 1001 % 500;//1

//        int g = 499 / 500;//0
//        int h = 500 / 500;//1
//        int i = 501 / 500;//1
//        int j = 999 / 500;//1
//        int k = 1000 / 500;//2
//        int l = 1001 / 500;//2

        int a = Math.floorDiv(499, 500);//0
        int b = Math.floorDiv(500, 500);//1
        int c = Math.floorDiv(501, 500);//1
        int d = Math.floorDiv(999, 500);//1
        int e = Math.floorDiv(1000, 500);//2
        int f = Math.floorDiv(1001, 500);//2
    }

    @Test
    public void random(){
        for (int i = 0; i < 100; i++) {
            int a = 1+ (int)(Math.random()*9);
            System.out.println(a);
        }
    }
}



