package com.example.exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CheckedException {
    public static void main(String[] args) {
        File file = new File("");
        try {
            // invoke chains: createNewFile() -> createFileExclusively()
            // -> native createFileExclusively() throws IOException

            // Exception class: IOException -> Exception -> Throwable

            // IOException checked exception: must try catch or throws
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
