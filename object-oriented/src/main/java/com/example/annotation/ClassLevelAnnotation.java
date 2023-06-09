package com.example.annotation;

import java.lang.annotation.*;

/**
 * ElementType: usage see source code
 * RetentionPolicy: usage see source code
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassLevelAnnotation {
    String name() default "default value";
    String version() default "1.1.0";
}