package com.jamie.deser;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class StudentDeserializable {
    private static final String PATH = "studentserializable.txt";
    public StudentDeserializable() {
        File file = new File(PATH);
        if(!file.exists()) {
            try {
                file.createNewFile();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream obj = new ObjectInputStream(fis);
            Student s = (Student) obj.readObject();
            System.out.println(s);
            obj.close();
            fis.close();
        }catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

