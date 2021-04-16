package com.jamie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyException {
    public static void main(String[] args) {

        //ArrayIndexOutOfBoundsException -...-> RuntimeException
        //运行时异常
//        byte[] bs = new byte[1];
//        bs[1] = 1;

        //检查一场
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
