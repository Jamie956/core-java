package com.lang_exception;

import java.io.FileNotFoundException;

public class KeywordThrow {
    // throw: throw checked exception and unchecked exception on method body

    public void foo() {
        throw new IllegalArgumentException();
    }
    public void foo2() throws FileNotFoundException {
        throw new FileNotFoundException();
    }
}
