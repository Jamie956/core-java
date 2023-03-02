package com.example.exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CheckedException {
    public static void main(String[] args) {
        File file = new File("");
        try {
            // createNewFile() throws IOException, must be checked
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // throws 声明多个方法会抛出的异常
    // throws checked exception
    public void foo() throws NoSuchFieldException {}
    public void foo2() throws FileNotFoundException {}
}
