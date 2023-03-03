package com.example.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericExtends {

    public static void main(String[] args) {
        GenericExtends o = new GenericExtends();
        // Integer extends Number
        o.fromArrayToList(new Integer("1"));
        // reason: no instance(s) of type variable(s) exist so that String conforms to Number
//        o.fromArrayToList("s");

        List<ParentG> src = new ArrayList<>();
        List<ChildG> dest = new ArrayList<>();
        copy(src, dest);

        List<Integer> dest2 = new ArrayList<>();
        //reason: no instance(s) of type variable(s) exist so that Integer conforms to ParentG
//        copy(src, dest2);
    }

    public <T extends Number> List<T> fromArrayToList(T a) {
        return null;
    }


    public static <G> void copy(List<? super G> dest, List<? extends G> src) {
    }
}

class ParentG {

}

class ChildG extends ParentG {

}