package com.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {
    @Test
    public void now() {
        LocalDate now = LocalDate.now();
    }

    @Test
    public void format() {
        LocalDate now = LocalDate.now();
        String f = now.format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    @Test
    public void plusDays() {
        LocalDate now = LocalDate.now();
        LocalDate l = now.plusDays(1);
    }

    @Test
    public void withDayOfMonth() {
        LocalDate now = LocalDate.now();
        LocalDate l = now.withDayOfMonth(1);
    }

    @Test
    public void parse() {
        LocalDate now = LocalDate.now();
        String f = now.format(DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate l = LocalDate.parse(f, DateTimeFormatter.BASIC_ISO_DATE);
    }


}
