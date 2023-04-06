package com.example.jdk.util;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class OptionalTest {
    @Test
    public void empty() {
        Optional<String> a = Optional.empty();
    }

    @Test
    public void of() {
        Optional<String> a = Optional.of("a");
    }

    @Test
    public void ofNullable() {
        Optional<String> a = Optional.ofNullable("a");
        Optional<String> b = Optional.ofNullable(null);
    }

    @Test
    public void get() {
        Optional<String> a = Optional.ofNullable("a");
        String s = a.get();
    }

    @Test
    public void isPresent() {
        Optional<String> a = Optional.ofNullable("a");
        boolean b = a.isPresent();
    }

    @Test
    public void ifPresent() {
        Optional<String> a = Optional.ofNullable("a");
        Consumer<String> f = e -> System.out.println(e);
        a.ifPresent(f);
    }

    @Test
    public void filter() {
        Optional<String> a = Optional.ofNullable("a");
        Predicate<String> f = e -> !"a".equals(e);
        Optional<String> s = a.filter(f);
    }

    @Test
    public void map() {
        Optional<String> a = Optional.ofNullable("a");
        Function<String, String> f = e -> e + ".";
        Optional<String> s = a.map(f);
    }

    @Test
    public void orElse() {
        Optional<String> a = Optional.ofNullable(null);
        String s = a.orElse("");
    }


}
