package com.example.jdk.time;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDatreTimeTest {
    @Test
    public void now() {
        LocalDateTime now = LocalDateTime.now();
    }

    @Test
    public void format() {
        LocalDateTime now = LocalDateTime.now();
        String f = now.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Test
    public void atZone() {
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime utc = now.atZone(ZoneId.of("UTC"));
    }

}
