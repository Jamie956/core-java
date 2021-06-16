package com.jamie;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @Author: Zjm
 * @Date: 2021/6/16 17:18
 */
public class DateTest {
    @Test
    public void getDateString() {
        //获取格式化日期
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault()).format(Instant.now().minus(1, ChronoUnit.DAYS));
        System.out.println(date);

        // 获取当前日期
        LocalDate today = LocalDate.now();
        System.out.println(today);

        // 获取当前日期的前一天
        String yesterday = today.plusDays(-1).toString();
        System.out.println(yesterday);
    }
}
