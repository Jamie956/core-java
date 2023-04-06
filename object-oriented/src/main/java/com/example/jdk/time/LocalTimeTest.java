package com.example.jdk.time;

import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeTest {
    @Test
    public void now() {
        LocalTime now = LocalTime.now();
    }

    @Test
    public void isAfter() {
        LocalTime a = LocalTime.now();
        LocalTime b = LocalTime.now();
        boolean x = b.isAfter(a);
    }

    @Test
    public void format() {
        LocalTime a = LocalTime.now();
        String t = a.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
