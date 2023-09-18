package com.time;

import org.junit.Test;

import java.time.Instant;

public class InstantTest {
    @Test
    public void now(){
        Instant now = Instant.now();
    }

    @Test
    public void ofEpochMilli(){
        long t = System.currentTimeMillis();
        Instant now = Instant.ofEpochMilli(t);
    }

}
