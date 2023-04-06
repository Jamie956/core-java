package com.example.jdk.lang;

import org.junit.Test;

public class LongTest {
    @Test
    public void sd() {
        long maxValue = Long.MAX_VALUE;
        Class<Long> type = Long.TYPE;

        String a = Long.toString(16L, 2);
        String b = Long.toString(-16L, 2);
        String c = Long.toUnsignedString(16L, 2);
        String d = Long.toUnsignedString(-16L, 2);

        long e = Long.parseLong("16", 8);
    }
}
