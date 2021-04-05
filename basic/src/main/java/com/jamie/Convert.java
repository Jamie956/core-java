package com.jamie;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Convert {
    @Test
    public void getDateString() {
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.systemDefault()).format(Instant.now().minus(1, ChronoUnit.DAYS));

        // 获取当前日期
        LocalDate today = LocalDate.now();
        // 获取当前日期的前一天
        String yesterday = today.plusDays(-1).toString();
    }

    //html 转义
    @Test
    public void htmlConvert(){
        String str = "&nbsp;-&lt;-&gt;-&amp;-&quot;-&apos;";

        String unescapeHtml4 = StringEscapeUtils.unescapeHtml4(str);
        System.out.println(unescapeHtml4);
        String escapeHtml4 = StringEscapeUtils.escapeHtml4(unescapeHtml4);
        System.out.println(escapeHtml4);

        String unescapeHtml3 = StringEscapeUtils.unescapeHtml3(str);
        System.out.println(unescapeHtml3);
        String escapeHtml3 = StringEscapeUtils.escapeHtml3(unescapeHtml3);
        System.out.println(escapeHtml3);
    }



}
